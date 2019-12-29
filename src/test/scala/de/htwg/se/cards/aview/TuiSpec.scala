package de.htwg.se.cards.aview

import de.htwg.se.cards.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.cards.model.{DeckSingleton, Player, StatusFacade, Talon}
import org.scalatest.{Matchers, WordSpec}

class TuiSpec extends WordSpec with Matchers {
  "A Cards Tui" should {
    val queue = List(Player("Player 1", Nil), Player("Player 2", Nil))
    val talon = Talon(DeckSingleton.cards)
    val status = StatusFacade(talon, queue)
    val controller = new Controller(status)
    val tui = new Tui(controller)
    "try to play cards on input ([0-9]+ )+" in {
      tui.processInputLine("d")
      tui.processInputLine("0 ")
      controller.status.discard.head should be(status.talon.cards.head)
    }
    "undo/redo after playing a card" in {
      tui.processInputLine("z")
      controller.status.current.cards.head should be(status.talon.cards.head)
      tui.processInputLine("y")
      controller.status.discard.head should be(status.talon.cards.head)
    }
    "shuffle the Deck on input 's'" in {
      tui.processInputLine("s")
      controller.status.talon should not be talon
    }
    "add a Card to current player on input 'd'" in {
      tui.processInputLine("d")
      controller.status.current.cards.size should be(status.current.cards.size + 1)
    }

    "change player on input 'n'" in {
      tui.processInputLine("n")
      controller.status.current should be(status.queue.tail.head)
    }
    "do nothing on any other input" in {
      val old = controller.statusToString
      tui.processInputLine("99999")
      controller.statusToString should be(old)
    }
  }
}
