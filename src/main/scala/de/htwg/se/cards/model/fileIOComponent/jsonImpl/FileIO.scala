package de.htwg.se.cards.model.fileIOComponent.jsonImpl

import de.htwg.se.cards.Cards.{player1, player2, talon}
import de.htwg.se.cards.model.fileIOComponent.FileIOInterface
import de.htwg.se.cards.model.statusComponent.StatusInterface
import de.htwg.se.cards.util.MauRuleStrategy
import play.api.libs.json.{JsObject, Json}

class FileIO extends FileIOInterface {
  override def load: StatusInterface = ???

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
          ???
        ),
        "queue" -> Json.obj(
          ???
        ),
        "discard" -> Json.obj(
          ???
        ),
        "rule" -> Json.obj(
          ???
        )
      )
    )
  }
}
