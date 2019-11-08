package de.htwg.se.cards.model

import org.scalatest._
import org.junit.runner.RunWith

import scala.language.postfixOps

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when {
    "new" should {
      val player = Player("Your Name", Nil)
      "have a name" in {
        player.name should be("Your Name")
      }
      "have a nice String representation" in {
        player.toString should be("Your Name")
      }
      "Have no cards" in {
        player.cards should be (Nil)
      }
    }
    "gived a card" should {
      val talon = Talon(Deck().cards)
      val empty = Talon(Nil)
      val card = talon.drop()_2
      val c = empty.drop()_2
      val player = Player("Player 1", Nil)
      "have one more card" in {
        player.giveCard(card).cards.size should be(player.cards.size + 1)
      }
      "have not a cards more if droped from empty talon" in {
        player.giveCard(c).cards.size should be (player.cards.size)
      }
    }

  }


}
