package de.htwg.se.cards.model

import de.htwg.se.cards.util.RuleStrategy
import org.scalatest.{Matchers, WordSpec}

class StatusSpec extends WordSpec with Matchers {
  "A status" when {

    val player1 = Player("Player 1", Nil)
    val player2 = Player("Player 2", Nil)
    val talon = Talon(DeckSingleton.cards)
    val queue = List(player1, player2)
    val status = Status(talon, queue, rule = new MauRuleStrategy)
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
      "Have an empty discard Pile" in {
        status.discard should be(Nil)
      }
      "Have a Rule" in {
        status.rule.isInstanceOf[RuleStrategy] should be(true)
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
        drawStatus.queue.tail should be(status.queue.tail)
      }
    }
    "draw(n)" should {
      val drawStatus = status.draw(5)
      "shrink the talon" in {
        drawStatus.talon.cards.size should be(status.talon.cards.size - 5)
      }
      "not touch other players" in {
        drawStatus.queue.tail should be(status.queue.tail)
      }
    }
    "play" should {
      val playStatus = status.draw.draw.draw.play(2 :: Nil)
      "play the cards if allowed" in {
        playStatus.discard.head should be(status.talon.cards.head)
        playStatus.current.cards.head should be(status.draw.talon.cards.drop(1).head)
      }
      "not change anything, if cards not match rules" in {
        playStatus.play(0 :: 1 :: Nil) should be (playStatus)
      }
    }
  }
}
