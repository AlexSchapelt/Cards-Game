package de.htwg.se.cards

import com.google.inject.{Guice, Injector}
import de.htwg.se.cards.model._

import scala.io.StdIn.readLine
import de.htwg.se.cards.aview._
import de.htwg.se.cards.aview.gui.SwingGui
import de.htwg.se.cards.controller.controllerComponent.ControllerInterface
import de.htwg.se.cards.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.cards.model.playerComponent.PlayerInterface
import de.htwg.se.cards.model.playerComponent.playerImpl.Player
import de.htwg.se.cards.model.statusComponent.StatusInterface
import de.htwg.se.cards.model.statusComponent.statusImpl.StatusFacade
import de.htwg.se.cards.model.talonComponent.TalonInterface
import de.htwg.se.cards.model.talonComponent.talonImpl.Talon
import de.htwg.se.cards.util.{DeckSingleton, MauRuleStrategy, PresidentRule}

import scala.util.{Failure, Success, Try}

object Cards {
  val injector: Injector = Guice.createInjector(new CardsModule)
  val talon: TalonInterface = injector.getInstance(classOf[TalonInterface])
  val status: StatusInterface = injector.getInstance(classOf[StatusInterface])
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])

  //val tui = new Tui(controller)
  val gui = new SwingGui(controller)


  def main(args: Array[String]): Unit = {
    var input: String = "init" //args(0)

    Context.handle(input)
    object Context {
      var state: Unit = initGame()

      def handle(e: String): Unit = {
        e match {
          case "init" => state = initGame()
          //case _ => state = play()
        }
      }

      def initGame(): Unit = {
        controller.preInit()
        //state = play()
      }

      /*def play(): Unit = {
        while (input != "q") {
          tui.processInputLine(input)
          input = readLine("Input: ")
        }
      }*/
    }
  }

  private def toInt(s: String): Try[Int] = {
    val value: Try[Int] = Try(s.toInt)
    value.filter(x => x <= 4 && x > 0)
  }
}