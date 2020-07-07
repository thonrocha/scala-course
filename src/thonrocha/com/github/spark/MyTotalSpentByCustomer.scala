package thonrocha.github.com.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

object MyTotalSpentByCustomer {

  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine, named TotalSpentByCustomer
    val sc = new SparkContext("local[*]", "MyTotalSpentByCustomer")

    // Load up each line of the customer orders data into an RDD
    val lines = sc.textFile("../scala-course/resources/data/customer/customer-orders.csv")
    val parsedLines = lines.map(parseLine)
    val customerTotals = parsedLines.reduceByKey((x,y) => x+y)
    
    val results = customerTotals.collect()
    
    val flippedResult = customerTotals.map( x => (x._2, x._1) ).sortByKey().collect()
    
    for (result <- results.sorted) {
       val customer = result._1
       val spentAmount = result._2
       val formattedAmount = f"$spentAmount%.2f"
       println(s"Customer $customer spent total amount: $formattedAmount") 
    }
    
    for (result <- flippedResult) {
       val customer = result._2
       val spentAmount = result._1
       val formattedAmount = f"$spentAmount%.2f"
       println(s"Customer $customer spent total amount: $formattedAmount") 
    }    

  }

  def parseLine(line: String) = {
    val fields = line.split(",")
    val customerId = fields(0).toInt
    val spentAmount = fields(2).toFloat
    (customerId, spentAmount)
  }
  
}