package de.htwg.se.cards.model.talonComponent.talonStubImpl

import de.htwg.se.cards.util.DeckSingleton
import org.scalatest.{Matchers, WordSpec}

class TalonSpec extends WordSpec with Matchers {
  "A Talon Stub" should {
    val talon = Talon()
    "alway has the full Deck as cards" in {
      talon.cards should be (DeckSingleton.cards)
    }
    "return itself and the top card after drop" in {
      val (t, c) = talon.drop()
      t should be (talon)
      c should be (Option(DeckSingleton.cards.head))
    }
    "return itself and the top 5 cards after drop" in {
      val (t, cs) = talon.drop(2)
      t should be (talon)
      cs should be (Option(DeckSingleton.cards.take(5)))
    }
    "return itself after other calls" in {
      talon.shuffle() should be (talon)
      talon.copyT() should be (talon)
    }
  }
}
