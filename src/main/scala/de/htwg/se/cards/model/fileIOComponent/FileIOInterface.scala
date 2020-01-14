package de.htwg.se.cards.model.fileIOComponent

import de.htwg.se.cards.model.statusComponent.StatusInterface

trait FileIOInterface {
  def load: StatusInterface

  def save(status: StatusInterface): Unit
}
