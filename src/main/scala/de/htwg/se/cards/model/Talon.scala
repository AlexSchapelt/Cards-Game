package de.htwg.se.cards.model

import scala.util.Random

case class Talon(cards: List[Card]) {
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
}
