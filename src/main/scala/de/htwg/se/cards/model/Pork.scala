package de.htwg.se.cards.model

case class Pork(nombre: String) {
    override def toString:String = nombre

  def name(): String = {
    nombre
  }
  def cook(): String = {
    "Bacon"
  }

}
