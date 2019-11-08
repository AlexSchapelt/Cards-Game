package de.htwg.se.cards.controller

import de.htwg.se.cards.model.{Deck, Player, Status, Talon}
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
      val status = Status(talon, queue)

      val controller = new Controller(status)

      val observer: Observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = updated = true
      }
      controller.add(observer)
      "notify its Observer after shuffle" in {
        controller.shuffle(talon)
        observer.updated should be (true)
        controller.status.talon should not be talon.shuffle()
      }
      "notify its Observer after showCards" in {
        //controller.showCards
        //observer.updated should be (true)

      }
      "notify its Observer after draw" in {
        //controller.draw
        //observer.updated should be (true)
      }
    }
  }
}
