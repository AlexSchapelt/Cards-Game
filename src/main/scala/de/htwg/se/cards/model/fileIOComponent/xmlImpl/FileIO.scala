package de.htwg.se.cards.model.fileIOComponent.xmlImpl

import java.io.{File, PrintWriter}

import de.htwg.se.cards.model.fileIOComponent.FileIOInterface
import de.htwg.se.cards.model.playerComponent.PlayerInterface
import de.htwg.se.cards.model.playerComponent.playerImpl.Player
import de.htwg.se.cards.model.statusComponent.StatusInterface
import de.htwg.se.cards.model.statusComponent.statusImpl.StatusFacade
import de.htwg.se.cards.model.talonComponent.TalonInterface
import de.htwg.se.cards.model.talonComponent.talonImpl.Talon
import de.htwg.se.cards.util.{Card, MauRuleStrategy}

import scala.xml.{Elem, Node, PrettyPrinter}

class FileIO extends FileIOInterface {
  override def load: StatusInterface = {
    val file: Elem = scala.xml.XML.loadFile("status.xml")
    val talon: TalonInterface = Talon(
      for {c <- (file \\ "status" \ "talon" \ "cards" \\ "card").toList} yield {
        xmlToCard(c)
      }
    )
    val queue: List[PlayerInterface] =
      for {p <- (file \\ "status" \ "queue" \\ "player").toList} yield {
        xmlToPlayer(p)
      }
    val discard: List[Card] =
      for {c <- (file \\ "status" \ "discard" \\ "card").toList} yield {
        xmlToCard(c)
      }
    StatusFacade(talon, queue, discard, new MauRuleStrategy())
  }

  def xmlToCard(elem: Node): Card = {
    Card((elem \ "@suit").text, (elem \ "@value").text)
  }

  def xmlToPlayer(elem: Node): PlayerInterface = {
    val cards: List[Card] =
      for {c <- (elem \ "cards" \\ "card").toList} yield {
        xmlToCard(c)
      }
    Player((elem \ "@name").text, cards)
  }

  override def save(status: StatusInterface): Unit = {
    val pw = new PrintWriter(new File("status.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(stateToXml(status))
    pw.write(xml)
    pw.close()
  }

  def stateToXml(s: StatusInterface): Elem = {
    <status>
      <talon>
        <cards>
          {s.talon.cards.map(c => cardToXml(c))}
        </cards>
      </talon>
      <queue>
        {s.queue.map(p => playerToXml(p))}
      </queue>
      <discard>
        {s.discard.map(c => cardToXml(c))}
      </discard>
      <rule>
      </rule>
    </status>
  }

  def cardToXml(c: Card): Elem = {
    <card suit={c.suit} value={c.value}>
    </card>
  }

  def playerToXml(p: PlayerInterface): Elem = {
    <player name={p.name}>
      <cards>
        {p.cards.map(c => cardToXml(c))}
      </cards>
    </player>
  }
}
