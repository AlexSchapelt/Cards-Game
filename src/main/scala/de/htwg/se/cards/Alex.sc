import scala.collection.mutable.ListBuffer



//tests:
val numbers = Seq(1,2,3)
val letters = Seq('a', 'b', 'c')
for {
  n <- numbers
  c <- letters
} yield (n, c)

//types:
var b = 123
b = 456


//collections
var list = List(1, 2, 3)
var i = list(1)
var arr = Array(1,2,3)
i = arr(2)
i
//Bounds
//arr(3) like java

//loops:
var j = 1
for (i <- 1 to 10) {
  j *= i
}
j
//with lists
j = 1
for (i <- list) {
  j *= i
}
j
//yield:
i = 0
var nums = List(-1, 12, -32, -4, 80, -1002)
var negs = for {/*generator*/i <- nums
                /*filter*/if i < 0
} yield {
  //
  i+1
}
negs
//nested:
i = 0
j = 0
var index = ListBuffer[String]()
for (i <- 0 to 5; j <- 0 to 5) {
  index += (i + " " + j)
  println(index)
}
index

//classes:
case class Test(/*val not*/name: String) {
  //body of class is constructor, if not defined as def, var or val:
  println("this class is called: " + name)
}
val t = Test("Bob")
//does not work: t.name = "Gustav"
val g=t.copy("Gustav")
println(t.name)
//this was edited in branch "test":
123 + 321 - 444

//this was edited in branch "anotherTestBranch":
println("this is a test for branches")

//this was added in branch "testbranches":
println("here is another test")