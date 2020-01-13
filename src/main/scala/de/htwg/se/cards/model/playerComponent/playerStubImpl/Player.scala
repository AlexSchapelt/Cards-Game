package de.htwg.se.cards.model.playerComponent.playerStubImpl

import de.htwg.se.cards.model.playerComponent.PlayerInterface
import de.htwg.se.cards.util
import de.htwg.se.cards.util.DeckSingleton

case class Player() extends PlayerInterface{
  private final val cardsSize = 5

  override def name: String = "Stub Player"

  override def cards: List[util.Card] = DeckSingleton.cards.take(cardsSize)

  override def giveCard(card: Option[util.Card]): PlayerInterface = this.copyP()

  override def giveCards(cards: Option[List[util.Card]]): PlayerInterface = this.copyP()

  override def copyP(name: String, cards: List[util.Card]): PlayerInterface = this
}
