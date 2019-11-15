package de.htwg.se.cards.model

import org.scalatest.{Matchers, WordSpec}

class DeckSpec extends WordSpec with Matchers {
  "A Deck" should {
    val d = Deck()
    "have a Card of a 32 Deck" in {
      d.cards.contains(Card("DIAMONDS", "7")) should be(true)
    }
    "have 32 Cards" in {
      d.cards.size should be(32)
    }
    "not have other cards" in {
      d.cards.contains(Card("sdhaksdh", "lksajd")) should be(false)
    }
  }
}
