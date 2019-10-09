package scalaTest

/**
  * @author jinxianbao
  * @date 2019/9/23 4:35 PM
  */
object Loop {
  def main(args: Array[String]): Unit = {
    //yield关键字，将i放到集合中返回
    val result = for(i <- 1 to 100; if(i > 5))yield i*i
    for(r <- result) println(r)
    result.foreach(println(_))
    //result.foreach(println) 同上

    //val a = for(i <- 5 to 60; if(i%5 == 0))yield i
    //小九九
    for(i<- 1 to 9;j<- 1 to i) {print(i+"*"+j+"="+i*j+"\t");if(i==j) println()}

  }

}
