package de.htwg.se.cards.aview.gui

import java.awt.Image

import de.htwg.se.cards.controller.controllerComponent.ControllerInterface
import de.htwg.se.cards.util.Card
import javax.swing.ImageIcon

import scala.swing.event.ButtonClicked
import scala.swing.{Button, Dimension}

case class CardButton(controller: ControllerInterface, index: Int) extends Button {
  ButtonClicked(this)
  name = index.toString
  private val d = new Dimension(100, 150)
  val card: Card = controller.status().current.cards.drop(index).head
  val img: Image = new ImageIcon("./res/cards/" + card.suit + "/" + card.value + ".png").getImage

  icon = new ImageIcon(img.getScaledInstance(d.width, d.height, java.awt.Image.SCALE_SMOOTH))
  preferredSize = d

  opaque = true
}