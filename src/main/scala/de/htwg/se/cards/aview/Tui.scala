package de.htwg.se.cards.aview

import de.htwg.se.cards.controller.Controller
import de.htwg.se.cards.util.Observer

import scala.util.Try

class Tui(controller: Controller) extends Observer {
  controller.add(this)

  def processInputLine(input: String): Unit = {
    val list: List[String] = input.split(" ").toList
    val toPlay: Try[List[Int]] = Try(list.map(x => x.toInt))//"([0-9]+ )+".r
    input match {
      //case "" => controller.addPlayer
      case "s" => controller.shuffle()
      case "n" => controller.nexPlayer()
      case "d" => controller.draw()
      case "z" => controller.undo()
      case "y" => controller.redo()
      case _ => if(toPlay.isSuccess) controller.play(toPlay.get)
    }
  }

  override def update: Boolean = {
    println(controller.statusToString)
    true
  }
}
