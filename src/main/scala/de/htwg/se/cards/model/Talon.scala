package de.htwg.se.cards.model
import scala.util.Random

case class Talon (cards: List[Card]) {
  def shuffle(): Talon = {
    Talon(Random.shuffle(this.cards))
  }
}
