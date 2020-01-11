package de.htwg.se.cards.util

import de.htwg.se.cards.model
import org.scalatest.{Matchers, WordSpec}

class RuleStrategySpec extends WordSpec with Matchers {
  "A Rule" when {
    "not implemented" should {
      "always allow to play the cards" in {
        val rule = new RuleStrategy {}
        rule.canPlay(Nil, Nil) should be(true)
      }
    }
    "implemented" should {
      val rule = new RuleStrategy {
        override def canPlay(toPlay: List[Card], played: List[Card]): Boolean = {
          if (played.nonEmpty) {
            false
          }
          else {
            true
          }
        }
      }
      "Only allow to play if cards Match rules" in {
        rule.canPlay(List(Card("Test", "test")), Nil) should be(true)
        rule.canPlay(Nil, List(Card("Test", "test"))) should be(false)
      }
    }
  }
}
