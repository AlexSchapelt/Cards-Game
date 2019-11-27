package de.htwg.se.cards.model

import de.htwg.se.cards.util.Rule

class MauRule extends Rule {
  private val initCards = 7

  override def canPlay(toPlay: List[Card], played: List[Card]): Boolean = {
    if (toPlay.size != 1) {
      false
    } else if (played.isEmpty) {
      true
    } else if (played.head.suit == toPlay.head.suit) {
      true
    } else if (played.head.value == toPlay.head.value) {
      true
    } else {
      false
    }
  }

  override def init(status: Status): Status = {
    val talon = Talon(Deck().cards).shuffle()
    val initStatus = status.copy(Talon(talon.cards.tail), discard = talon.cards.head :: Nil)
    initR(initStatus, 0)
  }

  @scala.annotation.tailrec
  private def initR(status: Status, finishedPlayer: Int): Status = {
    val afterDraw = status.draw(initCards).nextPlayer
    if (finishedPlayer >= status.queue.size - 1) {
      afterDraw
    } else {
      initR(afterDraw, finishedPlayer + 1)
    }
  }
}
