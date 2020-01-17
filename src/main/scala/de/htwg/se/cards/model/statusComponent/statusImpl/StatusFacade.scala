package de.htwg.se.cards.model.statusComponent.statusImpl

import com.google.inject.{Guice, Inject, Injector}
import de.htwg.se.cards.CardsModule
import de.htwg.se.cards.model.playerComponent.PlayerInterface
import de.htwg.se.cards.model.playerComponent.playerImpl.Player
import de.htwg.se.cards.model.statusComponent.StatusInterface
import de.htwg.se.cards.model.talonComponent.TalonInterface
import de.htwg.se.cards.model.talonComponent.talonImpl.Talon
import de.htwg.se.cards.util.{Card, RuleStrategy}

case class StatusFacade(talon: TalonInterface = Talon(Nil), queue: List[PlayerInterface] = Nil,
                        discard: List[Card] = Nil, rule: RuleStrategy = new RuleStrategy {}) extends StatusInterface {

  def this() = this(Guice.createInjector(new CardsModule).getInstance(classOf[TalonInterface]), Nil, Nil, new RuleStrategy {})

  override def draw: StatusInterface = {
    val (t, c) = talon.drop()
    this.copy(t, current.giveCard(c) :: queue.tail)
  }

  override def draw(num: Int): StatusInterface = {
    val (t, cs) = talon.drop(num)
    this.copy(t, current.giveCards(cs) :: queue.tail)
  }

  override def init: StatusInterface = rule.init(this)

  override def shuffle: StatusInterface = {
    this.copy(talon = talon.shuffle())
  }

  override def nextPlayer: StatusInterface = {
    this.copy(queue = queue.tail :+ queue.head)
  }

  override def current: PlayerInterface = {
    queue.head
  }

  override def play(list: List[Int]): StatusInterface = {
    val toPlay = for {i <- list if i < current.cards.size && i >= 0} yield {
      current.cards.drop(i).head
    }
    if (rule.canPlay(toPlay, discard)) {
      this.copy(queue = current.copyP(cards = current.cards.diff(toPlay)) :: queue.tail, discard = toPlay ++ discard)
    } else {
      this
    }
  }

  override def copyS(talon: TalonInterface, queue: List[PlayerInterface], discard: List[Card], rule: RuleStrategy): StatusInterface = {
    this.copy(talon, queue, discard, rule)
  }
}
