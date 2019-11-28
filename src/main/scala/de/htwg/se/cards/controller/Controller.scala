package de.htwg.se.cards.controller

import de.htwg.se.cards.model.StatusFacade
import de.htwg.se.cards.util.{Observable, UndoManager}

class Controller(var status: StatusFacade) extends Observable {
  private val undoManager = new UndoManager

  def init(): Unit = {
    status = status.init
    notifyObservers
  }

  def nexPlayer(): Unit = {
    status = status.nextPlayer
    notifyObservers
  }

  def draw(): Unit = {
    status = status.draw
    notifyObservers
  }

  def shuffle(): Unit = {
    status = status.shuffle
    notifyObservers
  }

  def play(i: List[Int]): Unit = {
    undoManager.doStep(new playCommand(this, i))
    notifyObservers
  }

  def undo(): Unit = {
    undoManager.undoStep()
    notifyObservers
  }

  def redo(): Unit = {
    undoManager.redoStep()
    notifyObservers
  }

  def statusToString: String = status.toString
}
