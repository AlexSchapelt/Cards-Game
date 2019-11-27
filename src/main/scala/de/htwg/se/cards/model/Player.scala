package de.htwg.se.cards.model

case class Player(name: String, cards: List[Card]) {
  def giveCard(card: Option[Card]): Player = {
    card match {
      case Some(x) => this.copy(cards = x :: cards)
      case None => this
    }
  }

  def giveCards(cards: Option[List[Card]]): Player = {
    cards match {
      case Some(x) => this.copy(cards = x ++ this.cards)
      case None => this
    }
  }

  override def toString: String = {
    name + "'s cards:\n" + cards
  }
}

