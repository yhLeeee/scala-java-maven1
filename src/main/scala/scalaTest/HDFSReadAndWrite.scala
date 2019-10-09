package scalaTest


import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author jinxianbao
  * @date 2019/9/24 9:38 AM
  */
object HDFSReadAndWrite {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("lyh").setMaster("local")
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("hdfs://localhost:9000/root/WordCount")
    rdd.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).foreach(println)
    rdd.saveAsTextFile("hdfs://localhost:9000/root/After_Reduce_WordCount")
  }
}
