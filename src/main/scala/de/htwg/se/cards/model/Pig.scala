package de.htwg.se.cards.model

case class Pig(name: String, isAlive: Boolean = true) {
    override def toString:String = name


  def fried(): String = {
    this.name + "is now bacon"
  }
  def run(): String = {
    this.name + "is running"
  }
}
