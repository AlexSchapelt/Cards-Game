package de.htwg.se.cards.model

import scala.io.Source

case class Status(talon: Talon, queue: List[Player]) {
  def draw: Status = {
    val (t, c) = talon.drop()
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
    talon.toString + current.toString + "\nenter:\n's' to shuffle the talon\n'd' to draw a card\n'n' to change the Player\n'q' to quit the game"
  }
}
