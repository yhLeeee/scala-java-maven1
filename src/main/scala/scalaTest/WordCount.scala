package scalaTest

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by mrwanghc on 2018/7/17.
  */
object WordCount {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("./src/main/scala/scalaTest/WordCount").flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
    rdd.foreach(println)
    rdd.saveAsTextFile("result")
  }
}
