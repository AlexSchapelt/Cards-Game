package de.htwg.se.cards.aview.Gui

import de.htwg.se.cards.aview.Gui.{CardButton, CardsPanel}

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.cards.controller._

import scala.io.Source._

class SwingGui(controller: Controller) extends Frame {
  override def closeOperation(): Unit = {
    System.exit(0)
  }


  listenTo(controller)
  title = "HTWG Cards"
  contents = new GridPanel(3, 1) {
    contents +=
      new FlowPanel() {
        contents += Button("dummy 1") {}
      }
    contents +=
      new PilePanel(controller)
    contents +=
      CardsPanel(controller)

  }


  visible = true
  repaint
}
