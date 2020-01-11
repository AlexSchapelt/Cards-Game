package de.htwg.se.cards.controller.controllerComponent.controllerImpl

import de.htwg.se.cards.model.playerComponent.PlayerInterface
import de.htwg.se.cards.util.{Card, Command}

class PlayCommand(controller: Controller, toPlay: List[Int]) extends Command {
  override def doStep(): Unit = {
    controller.status = controller.status.play(toPlay)
  }

  override def undoStep(): Unit = {
    val status = controller.status
    val played: List[Card] = status.discard.take(toPlay.size)
    val current: PlayerInterface = status.current.giveCards(Option(played))
    val queue: List[PlayerInterface] = current :: status.queue.tail
    val discard: List[Card] = status.discard.drop(toPlay.size)
    controller.status = controller.status.copyS(queue = queue, discard = discard)
  }

  override def redoStep(): Unit = {
    val rePlay = toPlay.indices.toList
    controller.status = controller.status.play(rePlay)
  }
}
