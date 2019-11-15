package de.htwg.se.cards.controller

import de.htwg.se.cards.model.Status
import de.htwg.se.cards.util.Observable

class Controller(var status: Status) extends Observable {
  def nexPlayer: Unit = {
    status = status.nextPlayer
    notifyObservers
  }

  def draw: Unit = {
    status = status.draw
    notifyObservers
  }

  def shuffle: Unit = {
    status = status.shuffle
    notifyObservers
  }

  def statusToString: String = status.toString
}
