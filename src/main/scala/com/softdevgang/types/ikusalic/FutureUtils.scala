package com.softdevgang.types.ikusalic

import scala.language.higherKinds
import scala.collection.generic.CanBuildFrom
import scala.concurrent.{Promise, ExecutionContext, Future}
import scala.util.{Failure, Success, Try}


object FutureUtils {

    implicit class AugmentedFuture[+A](private val future: Future[A]) extends AnyVal {

        final def mapTry[B](f: Try[A] => B)(implicit executor: ExecutionContext): Future[B] = {
            val p = Promise[B]()
            future.onComplete(result => p.complete(Try(f(result))))
            p.future
        }

        final def flatMapTry[B](f: Try[A] => Future[B])(implicit executor: ExecutionContext): Future[B] = {
            val p = Promise[B]()
            future.onComplete(result => p.completeWith(f(result)))
            p.future
        }
    }

    def traverseByPartitioning[A, B, M[_] <: TraversableOnce[_]]
            (in: M[A])
            (f: A => Future[B])
            (implicit
                cbfFailure: CanBuildFrom[M[A], (A, Throwable), M[(A, Throwable)]],
                cbfSuccess: CanBuildFrom[M[A], (A, B), M[(A, B)]],
                executor: ExecutionContext
            ): Future[(M[(A, Throwable)], M[(A, B)])] = {

        val computations: List[(A, Future[B])] =
            in.foldLeft(List.empty[(A, Future[B])]) { case (acc, untypedA) =>
                val a = untypedA.asInstanceOf[A]
                (a  -> f(a)) :: acc
            }.reverse

        val failures = cbfFailure(in)
        val successes = cbfSuccess(in)

        def accumulateResults(cs: List[(A, Future[B])]): Future[(M[(A, Throwable)], M[(A, B)])] = cs match {
            case (a, futureB) :: tail => futureB.flatMapTry { result =>
                result match {
                    case Success(b) => successes += a -> b
                    case Failure(e) => failures += a -> e
                }
                accumulateResults(tail)
            }
            case Nil => Future.successful(failures.result() -> successes.result())
        }

        accumulateResults(computations)
    }
}
