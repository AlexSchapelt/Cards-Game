package de.htwg.se.cards.aview

import de.htwg.se.cards.controller.Controller
import de.htwg.se.cards.util.Observer

class Tui(controller: Controller) extends Observer {
  controller.add(this)

  def processInputLine(input: String): Unit = {
    val toPlay = "([0-9]+ )+".r
    input match {
      //case "" => controller.addPlayer
      case "s" => controller.shuffle()
      case "n" => controller.nexPlayer()
      case "d" => controller.draw()
      case "z" => controller.undo()
      case "y" => controller.redo()
      case toPlay(x) => controller.play(input.split(" ").toList.map(x => x.toInt))
      case _ =>
    }
  }

  override def update: Boolean = {
    println(controller.statusToString)
    true
  }
}
