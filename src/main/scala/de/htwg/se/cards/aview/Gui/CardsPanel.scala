package de.htwg.se.cards.aview.Gui

import java.awt.{Color, Component, Graphics, Insets}

import de.htwg.se.cards.controller.{Controller, SelectionChanged}
import javax.swing.BorderFactory
import javax.swing.border.Border

import scala.swing.{Button, FlowPanel}
import scala.swing.event.ButtonClicked

case class CardsPanel(controller: Controller) extends FlowPanel {
  listenTo(controller)
  var selection: List[Int] = Nil
  val buttons = for {i <- controller.status.current.cards.indices.toList} yield {
    CardButton(controller, i)
  }
  buttons.foreach(x => {
    contents += x
  })

  val confirmButton = Button("play selected Cards") {controller.play(selection)}
  listenTo(confirmButton)
  contents += confirmButton


  reactions += {
    case e: SelectionChanged =>
      selection = (e.index :: selection).distinct
      contents(e.index).border = BorderFactory.createLineBorder(Color.BLACK, 5)
      repaint
  }
}
