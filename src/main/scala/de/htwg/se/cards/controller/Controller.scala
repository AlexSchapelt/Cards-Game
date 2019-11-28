package de.htwg.se.cards.controller

import de.htwg.se.cards.model.StatusFacade
import de.htwg.se.cards.util.Observable

class Controller(var status: StatusFacade) extends Observable {
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
    status = status.play(i)
    notifyObservers
  }

  def statusToString: String = status.toString
}
