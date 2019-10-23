package de.htwg.se.cards.model

import org.scalatest.{Matchers, WordSpec}

class Card32Spec extends WordSpec with Matchers {
  "A Card" when {
    "new" should {
      val card = Card32(7, 3)
      "have a value" in {
        card.value should be {
          7
        }
      }
      "have a suit" in {
        card.suit should be {
          3
        }
      }
      "have a nice String representation" in {
        card.toString should be("CLOVER 7")
      }
    }
  }
}
