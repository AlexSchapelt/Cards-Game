package de.htwg.se.cards.model.talonComponent.talonStubImpl

import de.htwg.se.cards.model.talonComponent.TalonInterface
import de.htwg.se.cards.util
import de.htwg.se.cards.util.DeckSingleton

case class Talon() extends TalonInterface{
  private final val dropSize = 5

  override def cards: List[util.Card] = DeckSingleton.cards

  override def drop(): (TalonInterface, Option[util.Card]) = (this, Option(this.cards.head))

  override def drop(number: Int): (TalonInterface, Option[List[util.Card]]) = (this, Option(cards.take(dropSize)))

  override def shuffle(): TalonInterface = this

  override def copyT(cards: List[util.Card]): TalonInterface = this
}
