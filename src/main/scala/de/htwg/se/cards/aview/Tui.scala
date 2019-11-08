package de.htwg.se.cards.aview

import de.htwg.se.cards.model.{Player, Talon}

class Tui {
  def processInputLine(input: String, t: Talon, p: Player): (Talon, Player) = {
    input match {
      case "s" => (t.shuffle(), p)
      case "v" =>
        println("Cads of " + p.name + ":\n" + p.cards)
        (t, p)
      case "d" =>
        val (talon, card) = t.drop()
        val pe = card match {
          case Some(x) => p.giveCard(card)
          case None =>
            println("No more Cards in Talon")
            p
        }
        (talon, pe)
      case _ => (t, p)
    }
  }
}
