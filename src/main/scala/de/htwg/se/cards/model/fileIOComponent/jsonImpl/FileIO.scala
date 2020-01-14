package de.htwg.se.cards.model.fileIOComponent.jsonImpl

import de.htwg.se.cards.model.fileIOComponent.FileIOInterface
import de.htwg.se.cards.model.playerComponent.PlayerInterface
import de.htwg.se.cards.model.playerComponent.playerImpl.Player
import de.htwg.se.cards.model.statusComponent.StatusInterface
import de.htwg.se.cards.model.statusComponent.statusImpl.StatusFacade
import de.htwg.se.cards.model.talonComponent.talonImpl.Talon
import de.htwg.se.cards.util.{Card, MauRuleStrategy}
import play.api.libs.json.{JsObject, JsString, JsValue, Json, Writes}

import scala.io.Source

class FileIO extends FileIOInterface {
  override def load: StatusInterface = {
    val source: String
    = Source.fromFile("status.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    jsonToState(json)
  }

  def jsonToState(json: JsValue): StatusInterface = {
    val talon = Talon(
      for {
        c <- (json \ "status" \ "talon" \ "cards" \\ "card").toList
      } yield {
        jsonToCard(c)
      }
    )

    val queue: List[PlayerInterface] = for {
      p <- (json \ "status" \ "queue" \\ "player").toList
    } yield {
      jsonToPlayer(p)
    }

    val discard: List[Card] =
      for {
        c <- (json \ "status" \ "discard" \\ "card").toList
      } yield {
        jsonToCard(c)
      }

    val rule = new MauRuleStrategy

    StatusFacade(talon, queue, discard, rule)
  }

  def jsonToPlayer(elem: JsValue): PlayerInterface = {
    val name: String = (elem \ "name").as[String]
    val cards: List[Card] = for {
      c <- (elem \ "cards" \\ "card").toList
    } yield {
      jsonToCard(c)
    }
    Player(name, cards)
  }

  def jsonToCard(elem: JsValue): Card = {
    val suit: String = (elem \ "suit").as[String]
    val value: String = (elem \ "value").as[String]
    Card(suit, value)
  }

  override def save(status: StatusInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("status.json"))
    pw.write(Json.prettyPrint(statusToJson(status)))
    pw.close()
  }

  def statusToJson(status: StatusInterface): JsObject = {
    Json.obj(
      "status" -> Json.obj(
        //talon, queue = List(player1, player2), rule = new MauRuleStrategy
        "talon" -> Json.obj(
          "cards" -> status.talon.cards.map(x => Json.obj("card" -> Json.toJson(x)))),
        "queue" -> status.queue.map(x => Json.obj("player" -> Json.toJson(x))),
        "discard" -> status.discard.map(x => Json.obj("card" -> Json.toJson(x))),
        "rule" -> Json.obj()
      )
    )
  }

  implicit val cardWrites: Writes[Card] = (card: Card) => Json.obj(
    "suit" -> JsString(card.suit),
    "value" -> JsString(card.value),
  )

  implicit val playerWrites: Writes[PlayerInterface] = (player: PlayerInterface) => Json.obj(
    "name" -> JsString(player.name),
    "cards" -> player.cards.map(x => Json.obj("card" -> Json.toJson(x)))
  )
}
