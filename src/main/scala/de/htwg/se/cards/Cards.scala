package de.htwg.se.cards

import de.htwg.se.cards.model.Player

object Cards {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)
  }
}
