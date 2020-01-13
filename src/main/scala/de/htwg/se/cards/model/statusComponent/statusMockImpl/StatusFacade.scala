package de.htwg.se.cards.model.statusComponent.statusMockImpl

import de.htwg.se.cards.model.playerComponent.PlayerInterface
import de.htwg.se.cards.model.playerComponent.playerStubImpl.Player
import de.htwg.se.cards.model.statusComponent.StatusInterface
import de.htwg.se.cards.model.talonComponent.TalonInterface
import de.htwg.se.cards.model.talonComponent.talonImpl.Talon
import de.htwg.se.cards.util
import de.htwg.se.cards.util.{DeckSingleton, RuleStrategy}

case class StatusFacade(talon: TalonInterface) extends StatusInterface {
  private final val r = new RuleStrategy {}

  override def copyS(talon: TalonInterface, queue: List[PlayerInterface], discard: List[util.Card], rule: RuleStrategy): StatusInterface = {
    this.copy()
  }

  override def queue: List[PlayerInterface] = Player() :: Nil

  override def draw: StatusInterface = {
    val (t, c) = talon.drop()
    this.copy(talon = t)
  }

  override def draw(num: Int): StatusInterface = {
    val (t, c) = talon.drop(num)
    this.copy(talon = t)
  }

  override def init: StatusInterface = {
    this.copy(talon = Talon(DeckSingleton.cards).shuffle())
  }

  override def shuffle: StatusInterface = this.copy(talon = talon.shuffle())

  override def nextPlayer: StatusInterface = this

  override def current: PlayerInterface = this.queue.head

  override def play(list: List[Int]): StatusInterface = this

  override def rule: RuleStrategy = r

  override def discard: List[util.Card] = Nil
}
