package de.htwg.se.cards.aview.gui

import de.htwg.se.cards.controller.{Controller}
import javax.swing.ImageIcon

import scala.swing.event.ButtonClicked
import scala.swing.{Button, Dimension}

case class CardButton(controller: Controller, index: Int) extends Button {
  ButtonClicked(this)
  name = index.toString
  private val d = new Dimension(100, 150)
  val card = controller.status.current.cards.drop(index).head
  val img = new ImageIcon("./res/cards/" + card.suit + "/" + card.value + ".png").getImage

  icon = new ImageIcon(img.getScaledInstance(d.width, d.height, java.awt.Image.SCALE_SMOOTH))
  preferredSize = d

  opaque = true
}