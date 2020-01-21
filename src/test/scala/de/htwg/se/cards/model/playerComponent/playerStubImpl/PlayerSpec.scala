package de.htwg.se.cards.model.playerComponent.playerStubImpl

import de.htwg.se.cards.util.DeckSingleton
import org.scalatest.{Matchers, WordSpec}

class PlayerSpec extends WordSpec with Matchers {
  "A Player stub" should {
    val player = Player()
    "be named Stub Player" in {
      player.name should be ("Stub Player")
    }
    "always has the top 5 cards of the Deck" in {
      player.cards should be (DeckSingleton.cards.take(5))
    }
    "always return itself at all other functions" in {
      player.copyP() should be (player)
      player.giveCard(None) should be (player)
      player.giveCards(None) should be (player)
    }
  }
}
