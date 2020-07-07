package thonrocha.com.github.scala.exercise

object Exercise2 {
   // EXERCISE
	 // Write some code that prints out the first 10 values of the Fibonacci sequence.
	 // This is the sequence where every number is the sum of the two numbers before it.
	 // So, the result should be 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
	 
		var a = 0                         //> a  : Int = 0
	 	var p = 1                         //> p  : Int = 1
	 for (x <- 0 to 9){
	 if (x > 1){
	 		val f = a + p
			a = p
	 		p = f
	 		println(f)
	 	} else {
	 		println(x)
	 	}                                 //> 0
                                                  //| 1
                                                  //| 1
                                                  //| 2
                                                  //| 3
                                                  //| 5
                                                  //| 8
                                                  //| 13
                                                  //| 21
                                                  //| 34
	 }
}