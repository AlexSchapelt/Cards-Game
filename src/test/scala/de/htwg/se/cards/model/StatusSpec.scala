package de.htwg.se.cards.model

import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable

class StatusSpec extends WordSpec with Matchers {
  "A status" when {
    val player1 = Player("Player 1", Nil)
    val player2 = Player("Player 2", Nil)
    val talon = Talon(Deck().cards)
    val queue = List(player1, player2)
    val status = Status(talon, queue)
    "new" should {
      "Have a Talon" in {
        status.talon should be(talon)
      }
      "Have some Players" in {
        status.queue should be(queue)
      }
      "Have a current Player" in {
        status.current should be(player1)
      }
    }
    "next player" should {
      "return status with next player at front of que" in {
        status.nextPlayer.current should be(player2)
      }
      "return status with last player at end of queue" in {
        status.nextPlayer.queue.last should be(status.current)
      }
    }
    "shuffle" should {
      "return status with same sized talon" in {
        status.shuffle.talon.cards.size should be(status.talon.cards.size)
      }
    }
    "draw" should {
      val drawStatus = status.draw
      "shrink the talon" in {
        drawStatus.talon.cards.size should be(status.talon.cards.size - 1)
      }
      "not touch other players" in {
        drawStatus.queue.tail should be (status.queue.tail)
      }
    }
    /*"method call" should {
      "return new status" in {
        status.shuffle() should not be (status)
        status.draw() should not be (status)
      }
    }*/
  }
}
