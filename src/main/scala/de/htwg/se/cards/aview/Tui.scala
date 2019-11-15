package de.htwg.se.cards.aview

import de.htwg.se.cards.controller.Controller
import de.htwg.se.cards.util.Observer

class Tui (controller: Controller) extends Observer{
  controller.add(this)
  def processInputLine(input: String): Unit = {
    input match {
      case "s" => controller.shuffle
      //case "v" => controller.sh
      case "n" => controller.nexPlayer
      case "d" => controller.draw
      case _ =>
    }
  }

  override def update: Boolean = {
    println(controller.statusToString)
    true
  }
}
