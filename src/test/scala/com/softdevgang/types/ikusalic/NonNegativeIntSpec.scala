package com.softdevgang.types.ikusalic

class NonNegativeIntSpec extends UnitSpec {

    it("has no public constructor") {
        "new NonNegativeInt(42)" shouldNot typeCheck
    }

    it("can be used as integer") {
        val nni: NonNegativeInt = NonNegativeInt.zero
        (nni: Int) shouldBe 0
    }

    describe(".fromInt") {

        it("succeeds for non-negative integer") {
            val result = NonNegativeInt.fromInt(42)

            result.isSuccess shouldBe true
            result.get.value shouldBe 42
        }

        it("fails for negative integer") {
            intercept[NumberFormatException] {
                NonNegativeInt.fromInt(-42).get
            }.getMessage shouldBe "Expected non-negative integer, but found: -42"
        }
    }

    describe(".parse") {

        it("parses non-negative integer") {
            NonNegativeInt.parse("42").get.value shouldBe 42
        }

        it("fails for negative integer") {
            intercept[NumberFormatException] {
                NonNegativeInt.parse("-42").get
            }
        }

        it("fails for not-integer input") {
            intercept[NumberFormatException] {
                NonNegativeInt.parse("blah").get
            }
        }
    }
}
