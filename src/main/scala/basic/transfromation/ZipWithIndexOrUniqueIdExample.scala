package basic.transfromation

import org.apache.spark.sql.SparkSession

object ZipWithIndexOrUniqueIdExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("zip partition example").master("local[2]").getOrCreate()
    val sc = spark.sparkContext

    val inputRDD = sc.parallelize(1 to 9,3)

    println("-------------input rdd------------")
    inputRDD.mapPartitionsWithIndex((pid, iter)=>{
      iter.map( value => "PID: " + pid + ", value: " + value)
    }).foreach(println)

    var resultRDD = inputRDD.zipWithIndex()
    println(resultRDD.toDebugString)
    println("------------zip with index result rdd----------")
    resultRDD.mapPartitionsWithIndex((pid, iter)=>{
      iter.map( value => "PID: " + pid + ", value: " + value)
    }).foreach(println)
    resultRDD = inputRDD.zipWithUniqueId()
    println(resultRDD.toDebugString)
    println("------------zip with uniqueid result rdd----------")
    resultRDD.mapPartitionsWithIndex((pid, iter)=>{
      iter.map( value => "PID: " + pid + ", value: " + value)
    }).foreach(println)
  }
}
