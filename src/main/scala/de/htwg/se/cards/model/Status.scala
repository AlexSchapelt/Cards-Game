package de.htwg.se.cards.model

import scala.collection.mutable
import scala.io.Source

case class Status(talon: Talon, queue: mutable.Queue[Player]) {
  private val file = Source.fromFile("./statusCover.txt")
  private val cover = file.getLines.mkString
  file.close()

  def nextPlayer(): Status = {
    queue.enqueue(queue.dequeue())
    this
  }

  override def toString: String = {
    String.format(cover, queue.front)
  }
}
