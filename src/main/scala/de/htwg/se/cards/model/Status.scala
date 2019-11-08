package de.htwg.se.cards.model

import scala.io.Source

case class Status(talon: Talon, queue: List[Player]) {
  private val file = Source.fromFile("./statusCover.txt")
  private val cover = file.getLines.mkString
  file.close()

  def draw(): Status={

    for(p <- queue) {

    }
    this.copy()
  }

  def shuffle(): Status = {
    this.copy(talon = talon.shuffle())
  }

  def nextPlayer(): Status = {
    this.copy(queue = queue.tail :+ queue.head)
  }

  def current():Player = {
    queue.head
  }

  override def toString: String = {
    String.format(cover, queue.head)
  }
}
