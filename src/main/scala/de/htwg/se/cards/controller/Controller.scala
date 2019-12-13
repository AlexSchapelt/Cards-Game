package de.htwg.se.cards.controller

import de.htwg.se.cards.model.StatusFacade
import de.htwg.se.cards.util.UndoManager

import scala.swing.Publisher

class Controller(var status: StatusFacade) extends Publisher {
  private val undoManager = new UndoManager

  def init(): Unit = {
    status = status.init
    publish(new StatusChanged)
  }

  def nexPlayer(): Unit = {
    status = status.nextPlayer
    publish(new PlayerChanged)
  }

  def draw(): Unit = {
    status = if (status == status.draw) status.rule.fillTalon(status).draw else status.draw
    publish(new CardsChanged)
  }

  def shuffle(): Unit = {
    status = status.shuffle
    publish(new TalonChanged)
  }

  def play(i: List[Int]): Unit = {
    undoManager.doStep(new playCommand(this, i))
    publish(new CardsChanged)
    publish(new DiscardChanged)
  }

  def undo(): Unit = {
    undoManager.undoStep()
    publish(new StatusChanged)
  }

  def redo(): Unit = {
    undoManager.redoStep()
    publish(new StatusChanged)
  }

  def statusToString: String = status.toString
}
