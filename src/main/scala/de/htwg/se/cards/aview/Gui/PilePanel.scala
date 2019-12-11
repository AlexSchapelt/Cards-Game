package de.htwg.se.cards.aview.Gui

import java.awt.Dimension

import de.htwg.se.cards.controller.Controller
import javax.swing.ImageIcon

import scala.swing.{Button, Dimension, FlowPanel}

class PilePanel(controller: Controller) extends FlowPanel {
  contents += new Button() {
    controller.draw()

    val d = new Dimension(100, 150)
    val img = new ImageIcon("./res/cards/red_back.png").getImage

    icon = new ImageIcon(img.getScaledInstance(d.width, d.height, java.awt.Image.SCALE_SMOOTH))
    preferredSize = d
  }

  contents += new Button() {
    private val d = new Dimension(100, 150)
    val card = controller.status.discard.head
    val img = new ImageIcon("./res/cards/" + card.suit + "/" + card.value + ".png").getImage

    icon = new ImageIcon(img.getScaledInstance(d.width, d.height, java.awt.Image.SCALE_SMOOTH))
    preferredSize = d

    opaque = true
  }
}
