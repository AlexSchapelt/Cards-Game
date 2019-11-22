package de.htwg.se.cards

import de.htwg.se.cards.model._

import scala.io.StdIn.readLine
import de.htwg.se.cards.aview._
import de.htwg.se.cards.controller.Controller

object Cards {
  val player1 = Player("Player 1", Nil)
  val player2 = Player("Player 2", Nil)
  val talon = Talon(Deck().cards)
  //dval testTalon = Talon(Deck().cards.take(5))
  val s = Status(talon, List(player1, player2), rule = new MauRule)
  val controller = new Controller(s)
  val tui = new Tui(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    var input: String = "" //args(0)
    if (!input.isEmpty) tui.processInputLine(input)
    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}