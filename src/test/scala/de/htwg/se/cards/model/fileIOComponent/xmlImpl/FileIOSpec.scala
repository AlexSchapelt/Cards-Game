package de.htwg.se.cards.model.fileIOComponent.xmlImpl

import de.htwg.se.cards.model.playerComponent.playerImpl.Player
import de.htwg.se.cards.model.statusComponent.statusImpl.StatusFacade
import de.htwg.se.cards.model.talonComponent.talonImpl.Talon
import de.htwg.se.cards.util.{DeckSingleton, MauRuleStrategy}
import org.scalatest.{Matchers, WordSpec}

class FileIOSpec extends WordSpec with Matchers{
  val fileIO = new FileIO
  val status = StatusFacade(Talon(DeckSingleton.cards), List(Player("p1", Nil)), Nil, new MauRuleStrategy)
  "a FileIO after save and load" should {
    "restore the old status" in {
      fileIO.save(status)
      val n = status.init
      n.current.cards.size should not be status.current.cards.size
      fileIO.load.current.cards.size should be (status.current.cards.size)
    }
  }
}
