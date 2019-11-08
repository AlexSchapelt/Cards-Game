package de.htwg.se.cards.controller

import de.htwg.se.cards.model.{Player, Status, Talon}
import de.htwg.se.cards.util.Observable

class Controller(var status:Status) extends Observable{
  def shuffle(talon:Talon): Unit = {
    talon.shuffle()
    notifyObservers
  }
}
