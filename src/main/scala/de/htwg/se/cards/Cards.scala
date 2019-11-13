package de.htwg.se.cards

import de.htwg.se.cards.model._

import scala.io.StdIn.readLine
import de.htwg.se.cards.aview._
import de.htwg.se.cards.controller.Controller

object Cards {
  val player = new Player("Player 1", Nil)
  val talon = Talon(Deck().cards)
  val s = new Status(talon, List(player))
  val controller = new Controller(s)
  val tui = new Tui(controller)
  controller.notifyObservers
  def main(args: Array[String]): Unit = {
    var input: String = ""
    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}