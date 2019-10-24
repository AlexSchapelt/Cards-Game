package de.htwg.se.cards.model

import org.scalatest._

class PorkSpec extends WordSpec with Matchers {

  "An Pork" when {
    "new" should {
      val pork = Pork("nombre")
      "Have a name" in {
        pork.name should be("nombre")
        "Have a String name" in {
          pork.toString should be("nombre")
        }
      }
    }
    }
  "is cooking" should {
    pork.cook should be("Bacon")
  }
}
