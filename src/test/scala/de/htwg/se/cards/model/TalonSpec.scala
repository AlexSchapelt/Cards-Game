package de.htwg.se.cards.model

import org.scalatest.{Matchers, WordSpec}

class TalonSpec extends WordSpec with Matchers {
  "A Talon" when {
    val t32 = Talon(32)
    "new" should {
      "have a capacity of" in {
        t32.capacity should be {
          32
        }
      }
    }
  }
}
