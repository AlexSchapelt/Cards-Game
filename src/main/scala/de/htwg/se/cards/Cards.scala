package de.htwg.se.cards

import de.htwg.se.cards.model._
import scala.io.StdIn.readLine
import de.htwg.se.cards.aview._

object Cards {
  val tui = new Tui
  var talon = Talon(Deck().cards)
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)
    val divide = Rational(1, 2).divide(Rational(2, 3)).reduce()
    println(divide)
    println(Card("DIAMONDS", "Jack"))

    var input: String = ""
    do {
      input = readLine()
      talon = tui.processInputLine(input, talon)
    } while (input != "q")
  }
}