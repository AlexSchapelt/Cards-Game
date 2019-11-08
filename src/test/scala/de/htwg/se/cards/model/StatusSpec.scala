package de.htwg.se.cards.model

import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable

class StatusSpec extends WordSpec with Matchers {
  "A status" when {
    val player1 = Player("Player 1", Nil)
    val player2 = Player("Player 2", Nil)
    val talon = Talon(Deck().cards)
    val queue = mutable.Queue(player1, player2)
    val status = Status(talon, queue)
    "new" should {
      "Have a Talon" in {
        status.talon should be(talon)
      }
      "Have some Players" in {
        status.queue should be(queue)
      }
      "have a String representation of current Game-status" in {

      }
    }
    "next player" should {
      "return status with next player at front of que" in {
        status.nextPlayer().queue.front should be (player2)
      }
      "return status with last player at end of queue" in {
        status.nextPlayer().queue.last should be (status.queue.front)
      }
    }
    "has to draw" should {
      "adding a Card to current players card" in {

      }
    }
  }
}
