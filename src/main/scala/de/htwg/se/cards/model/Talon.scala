package de.htwg.se.cards.model

import scala.io.Source
import scala.util.Random

case class Talon(cards: List[Card]) {
  private val file = Source.fromFile("./talon.txt")
  private val cover = file.getLines.mkString
  file.close()
  def drop(): (Talon, Option[Card]) = {
    if (this.cards.isEmpty) {
      (this, None)
    } else {
      (Talon(this.cards.tail), Some(this.cards.head))
    }
  }

  def shuffle(): Talon = {
    Talon(Random.shuffle(this.cards))
  }

  override def toString: String = String.format(cover)
}
