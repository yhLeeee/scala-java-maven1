import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author jinxianbao
  * @date 2019/9/24 4:58 PM
  */
object FlatmapAndMap {
  def main(args: Array[String]): Unit = {
    val a = List((1,2),(3,4))
    val conf = new SparkConf().setAppName("lyh").setMaster("local")
    val sc = new SparkContext(conf)
    val rdd1 = sc.parallelize(a)
    rdd1.flatMap(x => List(x._1, x._2)).foreach(println(_))
    rdd1.flatMap(x => List(x._1, x._2)).sample(false, 0.5, 4).foreach(println)     //1 2 3 4
    //rdd1.map(x => List(x._1, x._2)).foreach(println)      =>List(1, 2)  List(3, 4)
    //rdd1.sortByKey(false).foreach(println)
    //rdd1.cache()
    rdd1.subtract(sc.parallelize(List((1,2)))).top(1).foreach(println(_))
    rdd1.cache()
    val rdd2 = sc.parallelize(List(1,1,2,3))
    rdd2.distinct()
  }
}
