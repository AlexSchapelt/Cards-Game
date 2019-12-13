package de.htwg.se.cards.util

import de.htwg.se.cards.model.{Card, StatusFacade}

trait RuleStrategy {
  def canPlay(toPlay: List[Card], played: List[Card]): Boolean = true
  def init(status: StatusFacade): StatusFacade = status
  def fillTalon(status: StatusFacade): StatusFacade = status
}
