package de.htwg.se.cards.model

case class Deck() {
  val suits = List("DIAMONDS", "HEARTS", "CLOVER", "SPADES")
  val values = List("7", "8", "9", "10", "JACK", "QUEEN", "KING", "ASS")
  val cardList: List[Card] = for {
    s <- suits
    v <- values
  } yield Card(s, v)

  def cards(): List[Card] = cardList
}
