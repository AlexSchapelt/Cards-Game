package de.htwg.se.cards.model
import scala.util.Random

case class Talon (cards: List[Card]) {
  def drop(): (Talon, Card) = {
    (Talon(cards.tail), cards.head)
  }

  def shuffle(): Talon = {
    Talon(Random.shuffle(this.cards))
  }
}
