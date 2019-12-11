package de.htwg.se.cards.aview.gui

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.cards.controller._

import scala.io.Source._

class SwingGui(controller: Controller) extends Frame {
  maximize()
  override def closeOperation(): Unit = {
    System.exit(0)
  }


  listenTo(controller)
  title = "HTWG Cards"

  val cards = new CardsPanel(controller)
  val pile = new PilePanel(controller)

  val mainLayout = new GridPanel(3, 1) {
    contents +=
      new FlowPanel() {
        contents += Button("next Player") {controller.nexPlayer()}
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
      mainLayout.contents(2) = cards
      contents = mainLayout
      repaint
    case e: DiscardChanged =>
      val pile = new PilePanel(controller)
      mainLayout.contents(1) = pile
      contents = mainLayout
      repaint
    case e: PlayerChanged =>
      val cards = new CardsPanel(controller)
      mainLayout.contents(2) = cards
      contents = mainLayout
      repaint
  }

  visible = true
  repaint
}
