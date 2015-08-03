package com.softdevgang.types.ikusalic

import java.util.concurrent.ForkJoinPool
import scala.concurrent.{ExecutionContext, Await, Future}
import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}


class FutureUtilsSpec extends UnitSpec {

    import FutureUtils._

    def assertFutureResult[T](future: Future[T], expected: T) = {
        val result = Await.result(future, 1.second)
        result shouldBe expected
    }

    describe(".mapTry") {

        import scala.concurrent.ExecutionContext.Implicits.global

        def tryToStr[A](arg: Try[A]): String = arg match {
            case Success(_) => "Success"
            case Failure(_) => "Failure"
        }

        it("wraps successful future result in Success") {
            val future = Future.successful(42).mapTry(tryToStr)

            assertFutureResult(future, "Success")
        }

        it("replaces failed future with successful future containing Failure(_)") {
            val future = Future.failed(new Exception).mapTry(tryToStr)

            assertFutureResult(future, "Failure")
        }
    }

    describe(".flatMapTry") {

        import scala.concurrent.ExecutionContext.Implicits.global

        def tryToFutureStr[A](arg: Try[A]): Future[String] = arg match {
            case Success(_) => Future.successful("Success")
            case Failure(_) => Future.successful("Failure")
        }

        it("wraps successful future result in Success") {
            val future = Future.successful(42).flatMapTry(tryToFutureStr)

            assertFutureResult(future, "Success")
        }

        it("replaces failed future with successful future containing Failure(_)") {
            val future = Future.failed(new Exception).flatMapTry(tryToFutureStr)

            assertFutureResult(future, "Failure")
        }
    }

    describe(".traverseByPartitioning") {

        it("executes calculations in parallel") {
            case class Badness(s: String) extends RuntimeException(s)

            implicit val executor = ExecutionContext.fromExecutor(new ForkJoinPool(10))

            val deadline = 300.milliseconds.fromNow
            def slowUppercase(s: String) = Future {
                Thread.sleep(deadline.timeLeft.toMillis)
                if (s.contains("bad")) throw new Badness(s)
                else s.toUpperCase
            }

            val expected = (
                List("bad 1" -> Badness("bad 1"), "bad 2" -> Badness("bad 2")),
                List("a" -> "A", "b" -> "B", "c" -> "C")
            )
            assertResult(expected) {
                Await.result(
                    traverseByPartitioning(List("a", "bad 1", "b", "bad 2", "c"))(slowUppercase),
                    500.milliseconds
                )
            }
        }
    }
}
