package com.softdevgang.types.ikusalic

import scala.language.implicitConversions
import scala.util.{Failure, Success, Try}


class NonNegativeInt private (val value: Int) extends AnyVal {
    override def toString = value.toString
}

object NonNegativeInt {

    val zero = new NonNegativeInt(0)

    implicit def nonNegativeInt2int(nonNegative: NonNegativeInt): Int = nonNegative.value

    def fromInt(num: Int): Try[NonNegativeInt] =
        if (num >= 0) Success(new NonNegativeInt(num))
        else          Failure(new NumberFormatException(s"Expected non-negative integer, but found: $num"))

    def parse(s: String): Try[NonNegativeInt] =
        Try(s.toInt).flatMap(fromInt)
}
