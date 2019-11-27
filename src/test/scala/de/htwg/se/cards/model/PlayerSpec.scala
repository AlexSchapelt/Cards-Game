package de.htwg.se.cards.model

import org.scalatest._

import scala.language.postfixOps

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when {
    "new" should {
      val player = Player("Your Name", Nil)
      "have a name" in {
        player.name should be("Your Name")
      }
      "have a nice String representation" in {
        player.toString should be(player.name + "'s cards:\n" + player.cards)
      }
      "Have no cards" in {
        player.cards should be(Nil)
      }
    }
    "gived a card" should {
      val talon = Talon(Deck().cards)
      val empty = Talon(Nil)
      val card = talon.drop() _2
      val c = empty.drop() _2
      val player = Player("Player 1", Nil)
      "have one more card" in {
        player.giveCard(card).cards.size should be(player.cards.size + 1)
      }
      "have not a cards more if droped from empty talon" in {
        player.giveCard(c).cards.size should be(player.cards.size)
      }
    }
    "gived 5 cards" should {
      val talon = Talon(Deck().cards)
      val empty = Talon(Deck().cards.take(3))
      val player = Player("Player 1", Nil)
      "have one more card and shrinked the talon" in {
        val (t, cards) = talon.drop(5)
        player.giveCards(cards).cards.size should be(player.cards.size + 5)
        t.cards.size should be(talon.cards.size - 5)
      }
      "have not a cards more if droped from empty talon" in {
        val (t, cards) = empty.drop(5)
        player.giveCards(cards).cards.size should be(player.cards.size)
        t.cards.size should be (empty.cards.size)
      }
    }

  }


}
