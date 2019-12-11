package de.htwg.se.cards.controller

import scala.swing.event.Event

class StatusChanged extends Event

class CardsChanged extends Event

class PlayerChanged extends Event

class TalonChanged extends Event

class DiscardChanged extends Event

case class SelectionChanged(index: Int) extends Event
