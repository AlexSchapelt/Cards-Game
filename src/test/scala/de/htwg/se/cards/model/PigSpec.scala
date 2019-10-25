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
    "be alive" in {
      pig.isAlive should be(true)
    }
    "be running" in {
      pig.run should be("Pelusita is running")
    }
  }
  "dead" should {
    val pig = Pig("Pelusita")
    val deadPig = pig.copy(isAlive = false)
    "be dead" in {
      deadPig.isAlive should be (false)
    }
    "be fried" in {
      deadPig.fried should be("Pelusita is now bacon")
    }
  }
}
