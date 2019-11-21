package de.htwg.se.cards.util

import de.htwg.se.cards.model.Card

trait Rule {
  def canPlay(toPlay: List[Card], played: List[Card]): Boolean = true
}
