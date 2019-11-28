package de.htwg.se.cards.util

trait Command {
  def doStep(): Unit

  def undoStep(): Unit

  def redoStep(): Unit
}
