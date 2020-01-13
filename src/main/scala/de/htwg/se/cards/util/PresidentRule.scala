package de.htwg.se.cards.util

import de.htwg.se.cards.model.statusComponent.StatusInterface
import de.htwg.se.cards.model.talonComponent.talonImpl.Talon

class PresidentRule extends RuleStrategy {
  private val initCards = 7

  override def canPlay(toPlay: List[Card], played: List[Card]): Boolean = {
    if (toPlay.size > 4) {
      false
    } else if (!toPlay.forall(c => c.value == toPlay.head.value)) {
      false
    } else if (played.isEmpty) {
      true
    } else if(played.takeWhile(c => c.value == played.head.value).size != toPlay.size) {
      println("played: " + played + "\n" + "to play: " + toPlay + "\n" + "old Turns: " + played.takeWhile(c => c.value == played.head.value))
      false
    } else if (DeckSingleton.getPower(played.head) < DeckSingleton.getPower(toPlay.head)) {
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
    dealCards(status.copyS(talon = fullTalon), 0)
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
