package de.htwg.se.cards.model

case class Card32(value: Int, suit: Int) {
  private final val SUITS = Array("DIAMONDS", "HEARTS", "SPADES", "CLOVER")
  private final val VALUES = Array("7", "8", "9", "Jack", "Queen", "King", "Ass")
  private final val OFFSET = 7

  override def toString: String = SUITS(suit) + " " + VALUES(value - OFFSET)
}
