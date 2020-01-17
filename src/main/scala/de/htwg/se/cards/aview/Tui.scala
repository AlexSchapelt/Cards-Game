package de.htwg.se.cards.aview

import de.htwg.se.cards.controller.controllerComponent.{ControllerInterface, Init}
import de.htwg.se.cards.model.playerComponent.playerImpl.Player
import de.htwg.se.cards.util.MauRuleStrategy
import scala.io.StdIn.readLine
import scala.swing.Reactor
import scala.swing.event.Event
import scala.util.Try

class Tui(controller: ControllerInterface) extends Reactor {
  listenTo(controller)

  def processInputLine(input: String): Unit = {
    val list: List[String] = input.split(" ").toList
    val toPlay: Try[List[Int]] = Try(list.map(x => x.toInt))//"([0-9]+ )+".r
    input match {
      //case "" => controller.addPlayer
      case "s" => controller.shuffle()
      case "n" => controller.nextPlayer()
      case "d" => controller.draw()
      case "z" => controller.undo()
      case "y" => controller.redo()
      case "save" => controller.save()
      case "load" => controller.load()
      case _ => if(toPlay.isSuccess) controller.play(toPlay.get)
    }
  }

  reactions += {
    case e: Init =>
    case event =>  printTUI
  }

  def printTUI: Boolean = {
    println(controller.statusToString)
    true
  }
}
