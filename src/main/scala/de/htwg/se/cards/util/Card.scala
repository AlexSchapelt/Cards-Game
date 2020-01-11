package de.htwg.se.cards.util

import scala.io.Source

case class Card (suit: String, value: String){
  private val file = Source.fromFile("./cardLayout.txt")
  private val cover = file.getLines.mkString
  file.close()
}
