package de.htwg.se.cards.model

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
      Card(Deck().suits.head, Deck().values.head).value should be (Deck().values.head)
      Card(Deck().suits.head, Deck().values.head).suit should be (Deck().suits.head)
    }
  }
}
