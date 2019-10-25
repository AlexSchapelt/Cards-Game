package de.htwg.se.cards.model

case class Rational(num: Int, den: Int) {
  if (den == 0) throw new IllegalArgumentException("denominator can't be 0")

  private def getGCD(a: Int, b: Int): Int = {
    if (b == 0) a else getGCD(b, a % b)
  }

  def multiply(rational: Rational): Rational = Rational(rational.num * num, rational.den * den)

  def divide(rational: Rational): Rational = {
    val q = Rational(rational.den, rational.num)
    this.multiply(q)
  }

  def reduce(): Rational = {
    val gcd = getGCD(num, den)
    Rational(num / gcd, den / gcd)
  }
}
