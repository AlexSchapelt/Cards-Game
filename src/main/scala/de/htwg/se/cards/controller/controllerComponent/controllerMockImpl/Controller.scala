package de.htwg.se.cards.controller.controllerComponent.controllerMockImpl

import de.htwg.se.cards.controller.controllerComponent.ControllerInterface
import de.htwg.se.cards.model.statusComponent.StatusInterface
import de.htwg.se.cards.model.statusComponent.statusMockImpl.StatusFacade
import de.htwg.se.cards.model.talonComponent.talonStubImpl.Talon

class Controller extends ControllerInterface {
  var status: StatusInterface = StatusFacade(Talon())

  override def init(): Unit = status = status.init

  override def nextPlayer(): Unit = status = status.nextPlayer

  override def draw(): Unit = status = status.draw

  override def shuffle(): Unit = status = status.shuffle

  override def play(i: List[Int]): Unit = status = status.play(i)

  override def undo(): Unit = {}

  override def redo(): Unit = {}

  override def statusToString: String = status.toString
}
