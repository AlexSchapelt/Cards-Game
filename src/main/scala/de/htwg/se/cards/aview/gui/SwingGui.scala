package de.htwg.se.cards.aview.gui

import de.htwg.se.cards.controller.controllerComponent._
import de.htwg.se.cards.controller.controllerComponent.controllerImpl
import de.htwg.se.cards.model.playerComponent.playerImpl.Player
import de.htwg.se.cards.util.MauRuleStrategy

import scala.swing._
import scala.swing.event._
import scala.util.Try


class SwingGui(controller: ControllerInterface) extends Frame {
  private val cardsPos = 2
  private val pilesPos = 1
  override def closeOperation(): Unit = {
    System.exit(0)
  }
  listenTo(controller)
  title = "HTWG Cards"

  private def cards = new CardsPanel(controller)
  private def pile = new PilePanel(controller)
  private def mainLayout: GridPanel = new GridPanel(3, 1) {
    contents +=
      new FlowPanel() {
        contents += Button("next Player") {
          controller.nextPlayer()
        }
      }
    contents +=
      pile
    contents +=
      cards
  }

  //contents = mainLayout

  reactions += {
    case e: CardsChanged =>
      val cards = new CardsPanel(controller)
      mainLayout.contents(cardsPos) = cards
      contents = mainLayout
    //repaint
    //maximize()
    case e: DiscardChanged =>
      val pile = new PilePanel(controller)
      mainLayout.contents(pilesPos) = pile
      contents = mainLayout
    case e: PlayerChanged =>
      val cards = new CardsPanel(controller)
      mainLayout.contents(cardsPos) = cards
      contents = mainLayout
    case e: StatusChanged =>
      val cards = new CardsPanel(controller)
      mainLayout.contents(cardsPos) = cards
      val pile = new PilePanel(controller)
      mainLayout.contents(pilesPos) = pile
      contents = mainLayout
    case e: Init =>
      var n = toInt(getPlayer)
      while (n.isFailure) {
        n = toInt(getPlayer)
      }
      val queue = for {x <- Range(1, n.get + 1).toList} yield {
        Player("Player " + x, Nil)
      }
      controller.init(queue, new MauRuleStrategy)
    case e: PlayerWon =>
      contents = new Label(controller.status().current.name + " Won! go to File->New to play another round")
  }

  private def getPlayer: String = {
    contents = new FlowPanel()
    var in = Dialog.showInput(contents.head, "how many player would like to play?", initial = "2")
    while(in.isEmpty) {
      in = Dialog.showInput(contents.head, "how many player would like to play?", initial = "2")
    }
    in.get
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") {
        controller.preInit()
      })
      contents += new MenuItem(Action("Save") {
        controller.save()
      })
      contents += new MenuItem(Action("Load") {
        controller.load()
      })
      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") {
        controller.undo()
      })
      contents += new MenuItem(Action("Redo") {
        controller.redo()
      })
    }
  }

  visible = true
  //repaint

  private def toInt(s: String): Try[Int] = {
    val value: Try[Int] = Try(s.toInt)
    value.filter(x => x <= 4 && x > 0)
  }
}
