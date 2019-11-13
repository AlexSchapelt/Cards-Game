package de.htwg.se.cards.model

case class Player(name: String, cards: List[Card]) {

  def giveCard(card: Option[Card]): Player = {
    card match {
      case Some(x) => this.copy(cards = x :: cards)
      case None => this
    }
  }

  override def toString: String = {
    name + "'s cards:\n" + cards
  }
}

