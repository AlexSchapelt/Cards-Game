package de.htwg.se.cards.model.playerComponent.playerImpl

import de.htwg.se.cards.model.playerComponent.PlayerInterface
import de.htwg.se.cards.util.Card

case class Player(name: String, cards: List[Card]) extends PlayerInterface {
  override def giveCard(card: Option[Card]): PlayerInterface = {
    card match {
      case Some(x) => this.copy(cards = x :: cards)
      case None => this
    }
  }

  override def giveCards(cards: Option[List[Card]]): PlayerInterface = {
    cards match {
      case Some(x) => this.copy(cards = x ++ this.cards)
      case None => this
    }
  }

  override def copyP(name: String, cards: List[Card]): PlayerInterface = {
    this.copy(name, cards)
  }
}
