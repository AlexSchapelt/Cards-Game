package de.htwg.se.cards.aview.gui

import  scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.cards.controller._
import scala.io.Source._

//class CardClicked() extends Event

class SwingGui(controller: Controller) extends Frame {

  listenTo(controller)

  title = "HTWG Cards"

  val TalonPanel = new TextArea(controller.talon().toString)
  val DiscardPanel = new TextArea(controller.talon().toString) //TODO: Hier muss Discard rein!
  val TalonDiscardPanel = new FlowPanel(TalonPanel,DiscardPanel)

  val cardsPanel = new CardsPanel(controller.cards(),controller)

  contents = new BorderPanel {
    add(TalonDiscardPanel,BorderPanel.Position.Center)
    add(cardsPanel,BorderPanel.Position.South)
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") {controller.init()})
      contents += new MenuItem(Action("Quit"){System.exit(0)})
    }
    contents += new Menu("Edit"){
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo"){controller.undo()})
      contents += new MenuItem(Action("Redo"){controller.redo()})
    }
  }

  visible = true

  reactions += {
    case event: CardsChanged => redraw
  }

  def redraw = {

  }

}
