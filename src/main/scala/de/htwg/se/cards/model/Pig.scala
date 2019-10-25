package de.htwg.se.cards.model

case class Pig(nombre: String) {
    override def toString:String = name

  def name(): String = {
    this.nombre
  }
  def fried(): String = {
    this.name() + "is now bacon"
  }
  def run(): String = {
    this.name() + "is running"
  }
  def isAlive(): Boolean = {
    true
  }

}
