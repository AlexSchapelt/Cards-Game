package de.htwg.se.cards.util

import org.scalatest.{Matchers, WordSpec}

class CardSpec extends WordSpec with Matchers {
  "A Card" should {
    val card = Card("DIAMONDS", "7")
    "have a suit" in {
      card.suit should be ("DIAMONDS")
    }
    "have a value" in {
      card.value should be ("7")
    }
    "be accessable with Constructor" in {
      Card(DeckSingleton.suits.head, DeckSingleton.values.head).value should be (DeckSingleton.values.head)
      Card(DeckSingleton.suits.head, DeckSingleton.values.head).suit should be (DeckSingleton.suits.head)
    }
  }
}
