package de.htwg.se.cards.model

import de.htwg.se.cards.util.{Card, DeckSingleton}
import org.scalatest.{Matchers, WordSpec}

class DeckSingletonSpec extends WordSpec with Matchers {
  "A Deck" should {
    val d = DeckSingleton
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
