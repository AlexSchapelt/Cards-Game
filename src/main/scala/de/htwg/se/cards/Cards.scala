package de.htwg.se.cards

import de.htwg.se.cards.model._

import scala.io.StdIn.readLine
import de.htwg.se.cards.aview._
import de.htwg.se.cards.aview.gui.SwingGui
import de.htwg.se.cards.controller.Controller

import scala.util.{Failure, Success, Try}

object Cards {
  val player1 = Player("Player 1", Nil)
  val player2 = Player("Player 2", Nil)
  val talon = Talon(DeckSingleton.cards)
  //val testTalon = Talon(DeckSingleton.cards.take(5))
  val s = StatusFacade(talon, queue = List(player1, player2), rule = new MauRuleStrategy)
  val controller = new Controller(s)
  val tui = new Tui(controller)
  controller.init()
  val gui = new SwingGui(controller)
  //controller.notifyObservers

  def main(args: Array[String]): Unit = {
    var input: String = "init" //args(0)

    Context.handle(input)
    object Context {
      var state: Unit = initGame()

      def handle(e: String): Unit = {
        e match {
          case "init" => state = initGame()
          case _ => state = play()
        }
      }

      def initGame(): Unit = {
        var n = toInt(readLine("Enter number of players: "))
        while (n.isFailure) {
          n = toInt(readLine("Enter number of players: "))
        }
        val queue = for {x <- Range(1, n.get + 1).toList} yield {
          Player("Player " + x, Nil)
        }
        controller.status = controller.status.copy(queue = queue)
        controller.init()
        state = play()
      }

      def play(): Unit = {
        while (input != "q") {
          tui.processInputLine(input)
          input = readLine("Input: ")
        }
      }
    }
  }

  private def toInt(s: String): Try[Int] = {
    val value: Try[Int] = Try(s.toInt)
    value.filter(x => x <= 4 && x > 0)
  }
}