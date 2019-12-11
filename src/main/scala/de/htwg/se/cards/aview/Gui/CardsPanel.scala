package de.htwg.se.cards.aview.gui

import java.awt.{Color, Component, Graphics, Insets}
import java.io.ObjectInputFilter.Status

import de.htwg.se.cards.controller.{CardsChanged, Controller}
import javax.swing.BorderFactory
import javax.swing.border.Border

import scala.swing.{Button, FlowPanel}
import scala.swing.event.{ButtonClicked, SelectionChanged}

class CardsPanel(controller: Controller) extends FlowPanel {
  listenTo(controller)
  var selection: List[Int] = Nil
  val buttons = for {i <- controller.status.current.cards.indices.toList} yield {
    CardButton(controller, i)
  }
  buttons.foreach(x => {
    listenTo(x)
    contents += x
  })

  val confirmButton = Button("play selected Cards") {
    println(selection)
    controller.play(selection)
  }
  contents += confirmButton


  reactions += {
    case ButtonClicked(button: CardButton) =>
      selection = (button.index :: selection).distinct
      contents(button.index).border = BorderFactory.createLineBorder(Color.BLACK, 5)
      repaint
  }
}
