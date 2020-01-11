package de.htwg.se.cards.util

import de.htwg.se.cards.model.statusComponent.StatusInterface
import de.htwg.se.cards.model.talonComponent.talonImpl.Talon

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

  override def fillTalon(status: StatusInterface): StatusInterface = {
    status.copyS(talon = Talon(status.discard.tail), discard = status.discard.head :: Nil)
  }

  override def init(status: StatusInterface): StatusInterface = {
    val fullTalon = Talon(DeckSingleton.cards).shuffle()
    val discardTalonSatus = status.copyS(Talon(fullTalon.cards.tail), discard = fullTalon.cards.head :: Nil)
    dealCards(discardTalonSatus, 0)
  }

  override def won(status: StatusInterface): Boolean = {
    status.current.cards.isEmpty
  }

  @scala.annotation.tailrec
  private def dealCards(status: StatusInterface, finishedPlayer: Int): StatusInterface = {
    val resetCards = status.copyS(queue = status.queue.head.copyP(cards = Nil) :: status.queue.tail)
    val afterDraw = resetCards.draw(initCards).nextPlayer
    if (finishedPlayer >= status.queue.size - 1) {
      afterDraw
    } else {
      dealCards(afterDraw, finishedPlayer + 1)
    }
  }
}
