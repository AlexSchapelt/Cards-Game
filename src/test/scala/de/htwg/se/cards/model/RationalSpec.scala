package de.htwg.se.cards.model

import org.scalatest.{Matchers, WordSpec}

class RationalSpec extends WordSpec with Matchers {
  "A Rational" when {
    "new" should {
      an[IllegalArgumentException] should be thrownBy {
        val ex = Rational(1, 0)
      }
      val r = Rational(1, 2)
      "Have a numerator" in {
        r.num should be {
          1
        }
      }
      "Have a denominator" in {
        r.den should be {
          2
        }
      }
      "be a Rational" in {
        r shouldBe a[Rational]
      }
    }
    "reduced" should {
      val r = Rational(2, 6).reduce()
      "shorten the numerator" in {
        r.num should be {
          1
        }
      }
      "shorten the denominator" in {
        r.den should be {
          3
        }
      }
    }
    "multiplied" should {
      val m = Rational(2, 6).multiply(Rational(1, 2))
      "be equal" in {
        m should equal(Rational(2, 12))
      }
    }
    "divided" should {
      val d = Rational(2, 6).divide(Rational(1, 2))
      "be equal" in {
        d should equal(Rational(4, 6))
      }
    }
  }
}
