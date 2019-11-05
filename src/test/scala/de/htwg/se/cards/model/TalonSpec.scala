package de.htwg.se.cards.model

import org.scalatest.{Matchers, WordSpec}

class TalonSpec extends WordSpec with Matchers {
  "A Talon" when {
    val d = Deck().cards
    val talon = Talon(d)
    "filled" should {
      "should shuffle" in {
        assert(!talon.equals(talon.shuffle()))
      }
    }
    "droped" should {
      val (droped, card) = talon.drop()
      "have one card less" in {
        droped.cards.size should be {
          talon.cards.size -1
        }
      }
      "not contain droped card" in {
        droped.cards.contains(card) should be (false)
      }
      "still have same order" in {
        droped.cards should be (talon.cards.tail)
      }
    }
  }
}
