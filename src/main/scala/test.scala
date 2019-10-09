import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author yuhanli
  * @date 2019/10/8 2:48 PM
  */
object test {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("lyh").setMaster("local")
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(List(1,2,3,4,5,6)).map(_*3)
    val filteredRdd = rdd.filter(_ > 10).collect()
    println(filteredRdd.reduce(_+_))
    val a = for(i <- filteredRdd) yield i
    a.foreach(println(_))
    //for(i <- filteredRdd) println(i)
  }
}
