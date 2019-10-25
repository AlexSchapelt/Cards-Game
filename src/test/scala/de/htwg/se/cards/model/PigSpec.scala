package de.htwg.se.cards.model

import org.scalatest._

class PigSpec extends WordSpec with Matchers {

  "An Pig" when {
    "new" should {
        val pig = Pig("Pelusita")
      "Have a name" in {
        pig.name should be("Pelusita")
      }
      "Have a String name" in {
        pig.toString should be("Pelusita")
      }
    }
  }
  "alive" should {
    val pig = Pig("Pelusita")
    assert(pig.isAlive)
    "be running" in {
      pig.run should be("Pelusita is running")
    }
  }
  "dead" should {
    val pig = Pig("Pelusita")
    assert(!pig.isAlive)
    "be fried" in {
      pig.fried should be("Pelusita is now Bacon")
    }
  }
}
