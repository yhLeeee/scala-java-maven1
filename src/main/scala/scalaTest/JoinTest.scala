package scalaTest

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author jinxianbao
  * @date 2019/9/23 11:00 AM
  */
object JoinTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("lyh").setMaster("local")
    val sc = new SparkContext(conf)
    val a1 = List((2,(200,300)), (3,(400,500)), (4,(500,600)))
    val a2 = List((1,(1,4)), (3,(4,5)), (4,(5,6)), (3,(3,5)))
    val rdd1 = sc.parallelize(a1)
    val rdd2 = sc.parallelize(a2)
    rdd1.leftOuterJoin(rdd2).foreach(println)

    val rdd3 = rdd1.leftOuterJoin(rdd2)
    for(i <- 1 to 10){
      println(i)
    }
  }
}
