package de.htwg.se.cards.aview.gui

import java.awt.{Dimension, Image}

import de.htwg.se.cards.controller.controllerComponent.ControllerInterface
import de.htwg.se.cards.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.cards.model
import de.htwg.se.cards.util.Card
import javax.swing.ImageIcon

import scala.swing.{Action, Button, Dimension, FlowPanel}

class PilePanel(controller: ControllerInterface) extends FlowPanel {
  contents += new Button() {
    action = Action("") {
      controller.draw()
    }

    val d = new Dimension(100, 150)
    val img: Image = new ImageIcon("./res/cards/red_back.png").getImage

    icon = new ImageIcon(img.getScaledInstance(d.width, d.height, java.awt.Image.SCALE_SMOOTH))
    preferredSize = d
  }

  contents += new Button() {
    private val d = new Dimension(100, 150)
    if (controller.status().discard.nonEmpty) {
      val card: Card = controller.status().discard.head
      val img: Image = new ImageIcon("./res/cards/" + card.suit + "/" + card.value + ".png").getImage

      icon = new ImageIcon(img.getScaledInstance(d.width, d.height, java.awt.Image.SCALE_SMOOTH))
    }
    
    preferredSize = d

    opaque = true
  }
}
