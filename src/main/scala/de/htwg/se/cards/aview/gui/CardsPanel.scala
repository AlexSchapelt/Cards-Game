package de.htwg.se.cards.aview.gui

import de.htwg.se.cards.controller.Controller
import de.htwg.se.cards.model.Card

import scala.swing._
import javax.swing.table._

import scala.swing.event._


class CardsPanel(cards: List[Card], controller: Controller) extends FlowPanel {

  cards.splitAt(1)

  val card = new BoxPanel(Orientation.Vertical) {
    contents
  }

}


