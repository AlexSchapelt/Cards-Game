package de.htwg.se.cards.model
import org.scalatest._

class Animales extends WordSpec with Matchers {

    "Un Animal" when {
      "new" should {
        val animal = Animal("nombre")
      }
    }

}
