package de.htwg.se.cards.model

import scala.io.Source

case class Card (suit: String, value: String){
  private val file = Source.fromFile("./cardLayout.txt")
  private val cover = file.getLines.mkString
  file.close()

  override def toString: String = {
    String.format(cover, suit + value, suit + value, suit + value, suit+value)
  }
}
