package de.htwg.se.cards.aview.gui

import java.awt.Image

import de.htwg.se.cards.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.cards.model
import javax.swing.ImageIcon

import scala.swing.event.ButtonClicked
import scala.swing.{Button, Dimension}

case class CardButton(controller: Controller, index: Int) extends Button {
  ButtonClicked(this)
  name = index.toString
  private val d = new Dimension(100, 150)
  val card: model.Card = controller.status.current.cards.drop(index).head
  val img: Image = new ImageIcon("./res/cards/" + card.suit + "/" + card.value + ".png").getImage

  icon = new ImageIcon(img.getScaledInstance(d.width, d.height, java.awt.Image.SCALE_SMOOTH))
  preferredSize = d

  opaque = true
}