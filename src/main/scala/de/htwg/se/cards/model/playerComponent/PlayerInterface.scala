package de.htwg.se.cards.model.playerComponent

import de.htwg.se.cards.util.Card

trait PlayerInterface {
  def name: String

  def cards: List[Card]

  def giveCard(card: Option[Card]): PlayerInterface

  def giveCards(cards: Option[List[Card]]): PlayerInterface

  def copyP(name: String = name, cards: List[Card] = cards): PlayerInterface

  override def toString: String = {
    name + "'s cards:\n" + cards
  }
}
