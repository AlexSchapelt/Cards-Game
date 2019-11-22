package de.htwg.se.cards.model

import de.htwg.se.cards.util.Rule

class MauRule extends Rule {
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
}
