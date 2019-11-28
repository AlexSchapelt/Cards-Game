package de.htwg.se.cards.controller

import de.htwg.se.cards.model.{Card, Player}
import de.htwg.se.cards.util.Command

class playCommand(controller: Controller, toPlay: List[Int]) extends Command {
  override def doStep(): Unit = {
    controller.status = controller.status.play(toPlay)
  }

  override def undoStep(): Unit = {
    val status = controller.status
    val played: List[Card] = status.discard.take(toPlay.size)
    val current: Player = status.current.giveCards(Option(played))
    val queue: List[Player] = current :: status.queue.tail
    val discard: List[Card] = status.discard.drop(toPlay.size)
    controller.status = controller.status.copy(queue = queue, discard = discard)
  }

  override def redoStep(): Unit = {
    val rePlay = toPlay.indices.toList
    controller.status = controller.status.play(rePlay)
  }
}
