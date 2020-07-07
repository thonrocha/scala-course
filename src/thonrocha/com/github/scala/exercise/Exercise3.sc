package thonrocha.com.github.scala.exercise

object Exercise3 {
  // EXERCISE
  // Strings have a built-in .toUpperCase method. For example, "foo".toUpperCase gives you back FOO.
  // Write a function that converts a string to upper-case, and use that function of a few test strings.
  // Then, do the same thing using a function literal instead of a separate, named function.
  
  def uppercaseFunction(t : String) : String = {
  	t.toUpperCase
  }                                               //> uppercaseFunction: (t: String)String
  
	println(uppercaseFunction("adore delano"))//> ADORE DELANO
 	
	val t = (t:String) => t.toUpperCase       //> t  : String => String = thonrocha.com.github.scala.exercise.Exercise3$$$Lamb
                                                  //| da$8/1325808650@71bbf57e
	println(t("adore delano"))                //> ADORE DELANO
}