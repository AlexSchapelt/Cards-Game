"hi" + "there"
val a = "hi"
val b = (a,12, 12.5, 'c')
b._1
'a' + 1
'd' - 'c'
98.toChar
"a" * 3
42.toBinaryString

Int.MaxValue
Int.MinValue
Int.MaxValue+1
Byte.MaxValue
Short.MaxValue
Char.MaxValue
Char.MinValue
1000000000 + 1000000000
2000000000 + 2000000000
2000000000L + 2000000000L
(-42).toBinaryString
42.toOctalString
42.toHexString

Math.cos(Math.PI)
Math.log10(1000)
Math.random()
Math.PI
Math.E
Math.sqrt(9)

a.toUpperCase
a.compareToIgnoreCase("hi")
a.compareTo("hallo")

import java.util.LinkedList

val list = new LinkedList[String]()
list.add("")
list.add("hi")
list.add("Hola")
list.add("Pelusa")
list.add("Peluche")
if (list != null) {
  print(list)
}

val numList = new LinkedList[Int]()
var i = 0
while(i <= 10){
  numList.add(i)
  i = i+1
}

def printElement(list: LinkedList[Int]): Unit ={
  var sum = 0
  list.forEach(sum += _)
  print(sum)
}

printElement(numList)

class Value extends Enumeration{
  val Ten, Jack, Queen, King, Ace = Value
}

class Colour extends Enumeration{
  val Diamonds, Spades, Hearts, Clubs = Value
}

case class Card(colour:Colour, value:Value)

