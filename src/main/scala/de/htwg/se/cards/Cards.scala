package de.htwg.se.cards

import de.htwg.se.cards.model._

import scala.io.StdIn.readLine
import de.htwg.se.cards.aview._

import scala.collection.mutable

object Cards {
  val tui = new Tui
  var talon = Talon(Deck().cards)
  var player = Player("Player 1", Nil)
  var status = Status(talon, mutable.Queue(player))
  printf(status.toString)
  def main(args: Array[String]): Unit = {
    var input: String = ""
    do {
      input = readLine()
      val (t, p) = tui.processInputLine(input, talon, player)
      talon = t
      player = p
    } while (input != "q")
  }
}