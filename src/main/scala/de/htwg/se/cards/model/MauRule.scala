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
    var t = Talon(Deck().cards).shuffle()
    val q = for {p <- status.queue} yield {
      val (ts, cs) = t.drop(initCards)
      t = ts
      p.copy(cards = cs.get)
    }
    val (ts, c) = t.drop()
    t = ts
    val d = c.get :: status.discard

    status.copy(talon = t, queue = q, discard = d)
  }
}
