package de.htwg.se.cards.aview.gui

import de.htwg.se.cards.controller.controllerComponent._
import de.htwg.se.cards.controller.controllerComponent.controllerImpl.Controller

import scala.swing._
import scala.swing.event._


class SwingGui(controller: Controller) extends Frame {
  private val cardsPos = 2
  private val pilesPos = 1

  maximize()
  override def closeOperation(): Unit = {
    System.exit(0)
  }


  listenTo(controller)
  title = "HTWG Cards"

  val cards = new CardsPanel(controller)
  val pile = new PilePanel(controller)

  val mainLayout: GridPanel = new GridPanel(3, 1) {
    contents +=
      new FlowPanel() {
        contents += Button("next Player") {
          controller.nexPlayer()
        }
      }
    contents +=
      pile
    contents +=
      cards
  }

  contents = mainLayout

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
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") { controller.init() })
      contents += new MenuItem(Action("Quit") { System.exit(0) })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") { controller.undo() })
      contents += new MenuItem(Action("Redo") { controller.redo() })
    }
  }

  visible = true
  //repaint
}
