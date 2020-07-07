package thonrocha.com.github.scala.exercise

object ExerciseOne {
  // EXERCISE
  // Write some code that takes the value of pi, doubles it, and then prints it within a string with
  // three decimal places of precision to the right.
  // Just write your code below here; any time you save the file it will automatically display the results!
  
  val pi = 3.14159265f                            //> pi  : Float = 3.1415927
  val doublePi = pi * 2                           //> doublePi  : Float = 6.2831855
  println(f"The pi value * 2 is:  $doublePi%.3f") //> The pi value * 2 is:  6,283
}