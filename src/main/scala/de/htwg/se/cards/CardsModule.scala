package de.htwg.se.cards

import com.google.inject.{AbstractModule, Guice, Inject, Injector}
import com.google.inject.name.Names
import de.htwg.se.cards.controller.controllerComponent.ControllerInterface
import de.htwg.se.cards.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.cards.model.fileIOComponent.FileIOInterface
import de.htwg.se.cards.model.fileIOComponent._
import de.htwg.se.cards.model.playerComponent.PlayerInterface
import de.htwg.se.cards.model.playerComponent.playerImpl.Player
import de.htwg.se.cards.model.statusComponent.StatusInterface
import de.htwg.se.cards.model.statusComponent.statusImpl.StatusFacade
import de.htwg.se.cards.model.talonComponent.TalonInterface
import de.htwg.se.cards.model.talonComponent.talonImpl.Talon
import de.htwg.se.cards.util.DeckSingleton
import net.codingwell.scalaguice.ScalaModule

class CardsModule extends AbstractModule with ScalaModule {
  private var p: Int = 0
  private def players(): Int = {
    p +=1
    p
  }
  override def configure(): Unit = {
    //bind[StatusInterface].to[StatusFacade]
    bind[PlayerInterface].toInstance(Player("Player " + players(), Nil))
    bind[TalonInterface].toInstance(Talon(DeckSingleton.cards))
    bind[FileIOInterface].toInstance(new xmlImpl.FileIO())
  }
}
