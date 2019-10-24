package de.htwg.se.cards.model

class Rational(val num: Int, val den: Int) {
  def divide(rational: Rational): Rational = {
    val q = new Rational(rational.den, rational.num)
    this.multiply(q)
  }

  def multiply(rational: Rational): Rational = new Rational(rational.num * num, rational.den * den)

  private def getGCD(a: Int, b: Int): Int = {
    if (b == 0) a else getGCD(b, a % b)
  }

  def reduce(): Rational = {
    val gcd = getGCD(num, den);
    return new Rational(num / gcd, den / gcd)
  }
}
