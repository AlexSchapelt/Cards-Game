package de.htwg.se.cards.model

import de.htwg.se.cards.util.RuleStrategy

class MauRuleStrategy extends RuleStrategy {
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

  override def fillTalon(status: StatusFacade): StatusFacade = {
    status.copy(talon = Talon(status.discard.tail), discard = status.discard.head :: Nil)
  }

  override def init(status: StatusFacade): StatusFacade = {
    val fullTalon = Talon(DeckSingleton.cards).shuffle()
    val discardTalonSatus = status.copy(Talon(fullTalon.cards.tail), discard = fullTalon.cards.head :: Nil)
    dealCards(discardTalonSatus, 0)
  }

  @scala.annotation.tailrec
  private def dealCards(status: StatusFacade, finishedPlayer: Int): StatusFacade = {
    val afterDraw = status.draw(initCards).nextPlayer
    if (finishedPlayer >= status.queue.size - 1) {
      afterDraw
    } else {
      dealCards(afterDraw, finishedPlayer + 1)
    }
  }
}
