package de.htwg.se.cards.controller.controllerComponent.controllerImpl

import de.htwg.se.cards.controller.controllerComponent._
import de.htwg.se.cards.model.StatusFacade
import de.htwg.se.cards.util.UndoManager

class Controller(var status: StatusFacade) extends ControllerInterface {
  private val undoManager = new UndoManager

  override def init(): Unit = {
    status = status.init
    publish(new StatusChanged)
  }

  override def nexPlayer(): Unit = {
    status = status.nextPlayer
    publish(new PlayerChanged)
  }

  override def draw(): Unit = {
    status = if (status == status.draw) status.rule.fillTalon(status).draw else status.draw
    publish(new CardsChanged)
  }

  override def shuffle(): Unit = {
    status = status.shuffle
    publish(new TalonChanged)
  }

  override def play(i: List[Int]): Unit = {
    undoManager.doStep(new PlayCommand(this, i))
    publish(new CardsChanged)
    publish(new DiscardChanged)
  }

  override def undo(): Unit = {
    undoManager.undoStep()
    publish(new StatusChanged)
  }

  override def redo(): Unit = {
    undoManager.redoStep()
    publish(new StatusChanged)
  }

  override def statusToString: String = status.toString

}
