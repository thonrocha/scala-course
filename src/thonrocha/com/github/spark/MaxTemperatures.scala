package thonrocha.github.com.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.math.max

/** Find the maximum temperature by weather station for a year */
object MaxTemperatures {
  
  def parseLine(line:String)= {
    val fields = line.split(",")
    val stationID = fields(0)
    val entryType = fields(2)
    val temperature = fields(3).toFloat * 0.1f * (9.0f / 5.0f) + 32.0f
    (stationID, entryType, temperature)
  }
  
  def parseLineWithoutConversion(line:String)= {
    val fields = line.split(",")
    val stationID = fields(0)
    val entryType = fields(2)
    val entryValue = fields(3).toFloat
    (stationID, entryType, entryValue)
  }
  
    /** Our main function where the action happens */
  def main(args: Array[String]) {
   
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "MaxTemperatures")
    
    val lines = sc.textFile("../scala-course/resources/data/weather/1800.csv")
    val parsedLines = lines.map(parseLine)
    val maxTemps = parsedLines.filter(x => x._2 == "TMAX")
    val stationTemps = maxTemps.map(x => (x._1, x._3.toFloat))
    val maxTempsByStation = stationTemps.reduceByKey( (x,y) => max(x,y))
    val results = maxTempsByStation.collect()
    
    for (result <- results.sorted) {
       val station = result._1
       val temp = result._2
       val formattedTemp = f"$temp%.2f F"
       println(s"$station max temperature: $formattedTemp") 
    }
    
    val parsedLines2 = lines.map(parseLineWithoutConversion)
    val precipitations = parsedLines2.filter(x => x._2 == "PRCP")
    val stationPrcps = precipitations.map(x => (x._1, x._3.toFloat))
    val maxPrcpsByStation = stationPrcps.reduceByKey( (x,y) => max(x,y))
    val results2 = maxPrcpsByStation.collect()
    
    for (result <- results2.sorted) {
       val station = result._1
       val prcp = result._2
       val formattedPrcp = f"$prcp%.2f"
       println(s"$station max precipitation: $formattedPrcp") 
    }
      
  }
}