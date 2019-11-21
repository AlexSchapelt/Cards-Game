package de.htwg.se.cards.model

import de.htwg.se.cards.util.Rule

case class Status(talon: Talon, queue: List[Player], discard: List[Card], rule: Rule = new Rule {}) {
  def draw: Status = {
    val (t, c) = talon.drop()
    this.copy(t, queue.head.giveCard(c) :: queue.tail)
  }

  def shuffle: Status = {
    this.copy(talon = talon.shuffle())
  }

  def nextPlayer: Status = {
    this.copy(queue = queue.tail :+ queue.head)
  }

  def current: Player = {
    queue.head
  }

  def play(list: List[Int]): Status = {
    val toPlay = for {i <- list if i < current.cards.size && i >= 0} yield {
      current.cards.drop(i).head
    }
    if (rule.canPlay(toPlay, discard)) {
      this.copy(queue = current.copy(cards = current.cards.diff(toPlay)) :: queue.tail, discard = toPlay ++ discard)
    } else {
      this
    }
  }

  override def toString: String = {
    talon.toString + "Discard Pile:\n" + discard + "\n" + current.toString +
      "\nenter:\n" +
      "'s' to shuffle the talon\n" +
      "'d' to draw a card\n" +
      "'n' to change the Player\n" +
      "'q' to quit the game"
  }
}
