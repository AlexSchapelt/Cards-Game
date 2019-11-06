package de.htwg.se.cards.aview

import de.htwg.se.cards.model.Talon

class Tui {
  def processInputLine(input: String, t: Talon): Talon = {
    input match {
      case "s" => t.shuffle()
      case "v" =>
        println(t.cards)
        t
      case "d" =>
        val (talon, card) = t.drop()
        card match {
          case Some(x) => println(x)
          case None => println("No more Cards in Talon")
        }
        talon
      case _ => t
    }
  }
}
