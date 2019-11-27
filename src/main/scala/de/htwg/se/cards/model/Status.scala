package de.htwg.se.cards.model

import de.htwg.se.cards.util.RuleStrategy

case class Status(talon: Talon = Talon(Nil), queue: List[Player] = Nil, discard: List[Card] = Nil, rule: RuleStrategy = new RuleStrategy {}) {
  def draw: Status = {
    val (t, c) = talon.drop()
    this.copy(t, current.giveCard(c) :: queue.tail)
  }

  def draw(num: Int): Status = {
    val (t, cs) = talon.drop(num)
    this.copy(t, current.giveCards(cs) :: queue.tail)
  }

  def init: Status = rule.init(this)

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
      "'q' to quit the game\n" +
      "'([0-9] )+' to play a card"
  }
}
