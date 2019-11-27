package de.htwg.se.cards.util

import de.htwg.se.cards.model.{Card, Status}

trait RuleStrategy {
  def canPlay(toPlay: List[Card], played: List[Card]): Boolean = true
  def init(status: Status): Status = status
}
