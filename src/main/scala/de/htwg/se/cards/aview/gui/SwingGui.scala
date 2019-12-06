package de.htwg.se.cards.aview.gui

import  scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.cards.controller._
import scala.io.Source._

class SwingGui(controller: Controller) extends Frame {

  listenTo(controller)

  title = "HTWG Cards"


}
