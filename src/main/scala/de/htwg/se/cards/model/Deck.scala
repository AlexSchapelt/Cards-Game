package de.htwg.se.cards.model

case class Deck() {
  val suits = List("<>", "<3", " ^", " +")
  val values = List("7 ", "8 ", "9 ", "10", "J ", "Q ", "K ", "A ")
  val cards: List[Card] = for {
    s <- suits
    v <- values
  } yield Card(s, v)
}
