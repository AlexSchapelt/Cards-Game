package de.htwg.se.cards.aview.gui

import java.awt.Color

import de.htwg.se.cards.controller.controllerComponent.ControllerInterface
import javax.swing.BorderFactory

import scala.swing.{Button, FlowPanel}
import scala.swing.event.ButtonClicked

class CardsPanel(controller: ControllerInterface) extends FlowPanel {
  listenTo(controller)
  var selection: List[Int] = Nil
  val buttons: List[CardButton] = for {i <- controller.status().current.cards.indices.toList} yield {
    CardButton(controller, i)
  }
  buttons.foreach(x => {
    listenTo(x)
    contents += x
  })

  val confirmButton: Button = Button("play selected Cards") {
    println(selection)
    controller.play(selection)
  }
  contents += confirmButton


  reactions += {
    case ButtonClicked(button: CardButton) =>
      if (!selection.contains(button.index)) {
        selection = button.index :: selection
        contents(button.index).border = BorderFactory.createLineBorder(Color.BLACK, 5)
      } else {
        selection = selection.filter(_ != button.index)
        contents(button.index).border = BorderFactory.createEmptyBorder()
      }

      repaint
  }
}
