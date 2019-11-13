package de.htwg.se.cards.model

import scala.io.Source

case class Status(talon: Talon, queue: List[Player]) {
  def draw: Status = {
    val (t, c) = talon.drop()
    val p = queue.head.giveCard(c)
    this.copy(t, queue.head.giveCard(c) :: queue.tail)
  }

  def shuffle: Status = {
    this.copy(talon = talon.shuffle())
  }

  def nextPlayer: Status = {
    this.copy(queue = queue.tail :+ queue.head)
  }

  def current: Player = {
    queue.head
  }

  override def toString: String = {
    talon.toString + current.toString
  }
}
