package de.htwg.se.cards.model

import org.scalatest.{Matchers, WordSpec}

class MauRuleStrategySpec extends WordSpec with Matchers {
  "A MauRule" should {
    val maurule = new MauRuleStrategy
    "not allow to play more than one card" in {
      maurule.canPlay(DeckSingleton.cards.take(2), Nil) should be(false)
    }
    "allow to play any single card if no card was played" in {
      maurule.canPlay(DeckSingleton.cards.take(1), Nil)
    }
    "allow to play a single card if suits match" in {
      val toPlay = List(Card("TestSuit", "TestValue"))
      val played = Card("TestSuit", "asdasd") :: DeckSingleton.cards.take(5)
      maurule.canPlay(toPlay, played) should be (true)
    }
    "allow to play a single card if values match" in {
      val toPlay = List(Card("TestSuit", "TestValue"))
      val played = Card("asdasd", "TestValue") :: DeckSingleton.cards.take(5)
      maurule.canPlay(toPlay, played) should be (true)
    }
    "not allow mismatches" in {
      val toPlay = List(Card("TestSuit", "TestValue"))
      val played = Card("asdadsasd", "asdasd") :: DeckSingleton.cards.take(5)
      maurule.canPlay(toPlay, played) should be (false)
    }
    "initialize the game" in {
      val s = maurule.init(status = StatusFacade(queue = Player("Player 1", Nil)::Player("Player 2", Nil)::Nil))
      s.discard.size should be (1)
      s.queue.size should be (2)
      s.talon.cards.size should be (DeckSingleton.cards.size - 7 * s.queue.size - 1)
    }
  }
}
