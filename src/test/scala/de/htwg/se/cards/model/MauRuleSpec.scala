package de.htwg.se.cards.model

import org.scalatest.{Matchers, WordSpec}

class MauRuleSpec extends WordSpec with Matchers {
  "A MauRule" should {
    val maurule = new MauRule
    "not allow to play more than one card" in {
      maurule.canPlay(Deck().cards.take(2), Nil) should be(false)
    }
    "allow to play any single card if no card was played" in {
      maurule.canPlay(Deck().cards.take(1), Nil)
    }
    "allow to play a single card if suits match" in {
      val toPlay = List(Card("TestSuit", "TestValue"))
      val played = Card("TestSuit", "asdasd") :: Deck().cards.take(5)
      maurule.canPlay(toPlay, played) should be (true)
    }
    "allow to play a single card if values match" in {
      val toPlay = List(Card("TestSuit", "TestValue"))
      val played = Card("asdasd", "TestValue") :: Deck().cards.take(5)
      maurule.canPlay(toPlay, played) should be (true)
    }
    "not allow mismatches" in {
      val toPlay = List(Card("TestSuit", "TestValue"))
      val played = Card("asdadsasd", "asdasd") :: Deck().cards.take(5)
      maurule.canPlay(toPlay, played) should be (false)
    }
  }
}
