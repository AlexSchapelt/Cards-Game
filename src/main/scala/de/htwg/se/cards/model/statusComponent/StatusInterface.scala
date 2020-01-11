package de.htwg.se.cards.model.statusComponent

import de.htwg.se.cards.model.playerComponent.PlayerInterface
import de.htwg.se.cards.model.talonComponent.TalonInterface
import de.htwg.se.cards.model.talonComponent.talonImpl.Talon
import de.htwg.se.cards.util.{Card, RuleStrategy}

trait StatusInterface {
  def copyS(talon: TalonInterface = talon, queue: List[PlayerInterface] = queue, discard: List[Card] = discard, rule: RuleStrategy = rule): StatusInterface

  def queue: List[PlayerInterface]

  def rule: RuleStrategy

  def talon: TalonInterface

  def discard: List[Card]

  def draw: StatusInterface

  def draw(num: Int): StatusInterface

  def init: StatusInterface

  def shuffle: StatusInterface

  def nextPlayer: StatusInterface

  def current: PlayerInterface

  def play(list: List[Int]): StatusInterface

  override def toString: String = {
    talon.toString + "Discard Pile:\n" + discard + "\n" + current.toString +
      "\nenter:\n" +
      "'s' to shuffle the talon\n" +
      "'d' to draw a card\n" +
      "'n' to change the Player\n" +
      "'q' to quit the game\n" +
      "'([0-9]+ )+' to play a card"
  }
}
