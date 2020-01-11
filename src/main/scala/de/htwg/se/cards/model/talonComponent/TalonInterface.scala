package de.htwg.se.cards.model.talonComponent

import de.htwg.se.cards.util.Card


trait TalonInterface {
  def cards: List[Card]

  def drop(): (TalonInterface, Option[Card])

  def drop(number: Int): (TalonInterface, Option[List[Card]])

  def shuffle(): TalonInterface
}
