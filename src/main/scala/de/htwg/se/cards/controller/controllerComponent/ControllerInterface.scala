package de.htwg.se.cards.controller.controllerComponent

import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  def init(): Unit

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
