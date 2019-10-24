package de.htwg.se.cards.model

import org.scalatest.{Matchers, WordSpec}

class RationalSpec extends WordSpec with Matchers {
  "A Rational" when {
    "new" should {
      val r = new Rational(1, 2)
      "Have a numerator" in {
        r.num should be{
          1
        }
      }
      "Have a denominator" in {
        r.den should be {
          2
        }
      }
    }
  }
  "A Rational 2/6" when {
    "reduced" should {
      val r = new Rational(2, 6).reduce()
      "shorten the numerator" in {
        r.num should be {
          print(1)
          1
        }
      }
      "shorten the denominator" in {
        r.den should be {
          3
        }
      }
    }
  }
}
