package scalaTest

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author jinxianbao
  * @date 2019/9/20 7:39 PM
  */
object test {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("lyh").setMaster("local")
    val sc = new SparkContext(conf)
    sc.textFile("./src/test").foreach(x => println(x))
    sc.stop()
  }
}
