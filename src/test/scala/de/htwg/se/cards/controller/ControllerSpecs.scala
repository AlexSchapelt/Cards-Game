package de.htwg.se.cards.controller

import de.htwg.se.cards.model.{Deck, MauRuleStrategyStrategy, Player, Status, Talon}
import de.htwg.se.cards.util.Observer

import scala.language.reflectiveCalls
import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable

class ControllerSpecs extends WordSpec with Matchers {
  "A Controller" when {
    "observed by an Observer" should {
      val player1 = Player("Player 1", Nil)
      val player2 = Player("Player 2", Nil)
      val talon = Talon(Deck().cards)
      val queue = List(player1, player2)
      val status = Status(talon, queue, rule = new MauRuleStrategyStrategy)

      val controller = new Controller(status)

      val observer = new Observer {
        var updated: Boolean = false

        def isUpdated: Boolean = updated

        override def update: Boolean = {
          updated = true
          updated
        }
      }
      controller.add(observer)
      "notify its Observer after shuffle" in {
        controller.shuffle()
        observer.updated should be(true)
        controller.status.talon should not be talon.shuffle()
      }
      /*"notify its Observer after showCards" in {
        controller.showCards
        observer.updated should be (true)
      }*/
      "notify its Observer after draw" in {
        controller.draw()
        observer.updated should be(true)
        status.draw.talon.cards.size should be(status.talon.cards.size - 1)
        status.draw.current.cards.size should be(status.current.cards.size + 1)
      }
      "notify its Observer after nextPlayer" in {
        controller.nexPlayer()
        observer.updated should be(true)
        controller.status.current should be(status.queue.tail.head)
      }
      "notify its Observer after play" in {
        controller.play(0 :: Nil)
        observer.updated should be(true)
      }
      "notify its Observer after init" in {
        controller.init()
        observer.updated should be(true)
        controller.status.queue.head.cards.size should be(7)
      }
    }
  }
}
