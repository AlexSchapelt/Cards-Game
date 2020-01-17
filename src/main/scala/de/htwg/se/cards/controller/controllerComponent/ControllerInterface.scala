package de.htwg.se.cards.controller.controllerComponent

import de.htwg.se.cards.model.playerComponent.PlayerInterface
import de.htwg.se.cards.model.statusComponent.StatusInterface
import de.htwg.se.cards.util.RuleStrategy

import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  def preInit(): Unit

  def load(): Unit

  def save(): Unit

  def status(): StatusInterface

  def init(player: List[PlayerInterface], rule: RuleStrategy): Unit

  def nextPlayer(): Unit

  def draw(): Unit

  def shuffle(): Unit

  def play(i: List[Int]): Unit

  def undo(): Unit

  def redo(): Unit

  def statusToString: String
}

import scala.swing.event.Event

class StatusChanged extends Event

class CardsChanged extends Event

class PlayerChanged extends Event

class TalonChanged extends Event

class DiscardChanged extends Event

class Init extends Event

class PlayerWon extends Event