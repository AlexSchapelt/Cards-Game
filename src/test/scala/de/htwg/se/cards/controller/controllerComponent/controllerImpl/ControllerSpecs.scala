package de.htwg.se.cards.controller.controllerComponent
/*
import de.htwg.se.cards.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.cards.model.playerComponent.playerImpl.Player
import de.htwg.se.cards.model.statusComponent.statusImpl.StatusFacade
import de.htwg.se.cards.model.talonComponent.talonImpl.Talon
import de.htwg.se.cards.util.{DeckSingleton, MauRuleStrategy, Observer}
import org.scalatest.{Matchers, WordSpec}

import scala.language.reflectiveCalls

class ControllerSpecs extends WordSpec with Matchers {
  "A Controller" when {
    "observed by an Observer" should {
      val player1 = Player("Player 1", Nil)
      val player2 = Player("Player 2", Nil)
      val talon = Talon(DeckSingleton.cards)
      val queue = List(player1, player2)
      val status = StatusFacade(talon, queue, rule = new MauRuleStrategy)

      val controller = new Controller(status)

      val observer = new Observer {
        var updated: Boolean = false

        def isUpdated: Boolean = updated

        override def update: Boolean = {
          updated = true
          updated
        }
      }
      controller.listenTo(observer)
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
      "notify its Observer after play" in {
        controller.play(0 :: Nil)
        observer.updated should be(true)
      }
      "handle undo/redo for playing a card" in {
        val oldCards = controller.status.current.cards
        controller.undo()
        controller.status.current.cards.size should be (oldCards.size + 1)
        controller.redo()
        controller.status.current.cards.size should be (oldCards.size)
        controller.undo()
      }
      "notify its Observer after nextPlayer" in {
        controller.nextPlayer()
        observer.updated should be(true)
        controller.status.current should be(status.queue.tail.head)
      }
      "notify its Observer after init" in {
        controller.init()
        observer.updated should be(true)
        controller.status.queue.head.cards.size should be(7)
      }
    }
  }
}*/
