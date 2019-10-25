package de.htwg.se.cards.model

import org.scalatest.{Matchers, WordSpec}

class RationalSpec extends WordSpec with Matchers {
  "A Rational" when {
    "new" should {
      val r = new Rational(1, 2)
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
        r shouldBe a [Rational]
      }
    }
  }
  "A Rational 2/6" when {
    "reduced" should {
      val r = new Rational(2, 6).reduce()
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
      "be a Rational" in {
        r shouldBe a [Rational]
      }
    }
    "Multiplied with 1/2" should {
      val m = new Rational(2, 6).multiply(new Rational(1, 2))
      "have 2 as numerator" in {
        m.num should be {
          2
        }
      }
      "have 12 as denomintor" in {
        m.den should be {
          12
        }
      }
    }
    "divided by 1/2" should {
      val d = new Rational(2, 6).divide(new Rational(1, 2))
      "have 4 as numerator" in {
        d.num should be {
          4
        }
      }
      "have 6 as denominator" in {
        d.den should be {
          6
        }
      }
    }
  }
}
