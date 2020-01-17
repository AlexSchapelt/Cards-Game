package de.htwg.se.cards.controller.controllerComponent.controllerImpl

import de.htwg.se.cards.model.playerComponent.playerImpl.Player
import de.htwg.se.cards.model.statusComponent.statusImpl.StatusFacade
import de.htwg.se.cards.model.talonComponent.talonImpl.Talon
import de.htwg.se.cards.util.{DeckSingleton, MauRuleStrategy, Observer}
import org.scalatest.{Matchers, WordSpec}

import scala.language.reflectiveCalls
import scala.swing.{Publisher, Reactor}

class ControllerSpecs extends WordSpec with Matchers {
  "A Controller" when {
    "observed by an Observer" should {
      val player1 = Player("Player 1", Nil)
      val player2 = Player("Player 2", Nil)
      val talon = Talon(DeckSingleton.cards)
      val queue = List(player1, player2)
      val status = StatusFacade(talon, queue, rule = new MauRuleStrategy)

      val controller = new Controller(status)

      val reactor = new Reactor {
        listenTo(controller)
        var update = false
        reactions += {
          case event => update = true
        }

        def updated: Boolean = {
          val u = update
          update = false
          u
        }
      }
      "notify its Observer after shuffle" in {
        controller.shuffle()
        reactor.updated should be(true)
        controller.status.talon should not be talon.shuffle()
      }

      "notify its Observer after draw" in {
        controller.draw()
        reactor.updated should be(true)
        status.draw.talon.cards.size should be(status.talon.cards.size - 1)
        status.draw.current.cards.size should be(status.current.cards.size + 1)
      }
      "notify its Observer after play" in {
        controller.play(0 :: Nil)
        reactor.updated should be(true)
      }
      "handle undo/redo for playing a card" in {
        val oldCards = controller.status.current.cards
        controller.undo()
        controller.status.current.cards.size should be(oldCards.size + 1)
        controller.redo()
        controller.status.current.cards.size should be(oldCards.size)
        controller.undo()
      }
      "notify its Observer after nextPlayer" in {
        controller.nextPlayer()
        reactor.updated should be(true)
        controller.status.current should be(status.queue.tail.head)
      }
      "notify its Observer after init" in {
        controller.init(List(Player("p1", Nil)), new MauRuleStrategy)
        reactor.updated should be(true)
        controller.status.queue.head.cards.size should be(7)
      }
      "notify its Observer after preInit" in {
        controller.preInit()
        reactor.updated should be (true)
      }
      "not notify its Observer after save" in {
        controller.save()
        reactor.updated should be(false)
      }
      "notify its observer after load" in {
        val old = controller.status
        controller.draw()
        controller.load()
        reactor.updated should be(true)
      }
    }
  }
}
