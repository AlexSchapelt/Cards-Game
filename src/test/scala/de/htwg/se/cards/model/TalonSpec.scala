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
  }
}
