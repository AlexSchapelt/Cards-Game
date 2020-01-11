package de.htwg.se.cards.util

import de.htwg.se.cards.model.statusComponent.StatusInterface


trait RuleStrategy {
  def canPlay(toPlay: List[Card], played: List[Card]): Boolean = true
  def init(status: StatusInterface): StatusInterface = status
  def fillTalon(status: StatusInterface): StatusInterface = status
  def won(status: StatusInterface): Boolean = false
}
