package de.htwg.se.cards.model

case class Deck() {
  val suits = List("DIAMONDS", "HEARTS", "CLOVER", "SPADES")
  val values = List("7", "8", "9", "10", "JACK", "QUEEN", "KING", "ASS")
  val cards: List[Card] = for {
    s <- suits
    v <- values
  } yield Card(s, v)
}
