package de.htwg.se.cards.util

object DeckSingleton {
  val suits = List("DIAMONDS", "HEARTS", "SPADES", "CLOVES")
  val values = List("7", "8", "9", "10", "Jack", "Queen", "King", "Ass")
  val cards: List[Card] = for {
    s <- suits
    v <- values
  } yield Card(s, v)

  def getPower(card: Card): Int = {
      values.indexOf(card.value)
  }
}
