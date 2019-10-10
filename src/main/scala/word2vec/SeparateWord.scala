package word2vec

import com.hankcs.hanlp.tokenizer.NLPTokenizer
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author yuhanli
  * @date 2019/10/8 3:40 PM
  */
object SeparateWord {
  def segment(sc:SparkContext): Unit ={
    //stop words
    //val stopWordPath = "停用词路径"
    //val bcStopWords = sc.broadcast(sc.textFile(stopWordPath).collect().toSet)

    //segment
    val inPath = "/Users/JackKing/Desktop/news.txt"
    val segmentRes = sc.textFile(inPath)
      .map(AsciiUtil2.sbc2dbcCase(_)) //全角转半角
      .mapPartitions(it => {
      it.map(ct => {
        try{
          val nlpList = NLPTokenizer.segment(ct)
          import scala.collection.JavaConverters._
          nlpList.asScala.map(term => term.word)
            //.filter(!bcStopWords.value.contains(_))
            .mkString(" ")
        }catch {
          case e:NullPointerException => println(e);""
        }
      })
    })

    //save segment result
    segmentRes.saveAsTextFile("/Users/JackKing/Desktop/news_cut.txt")
    //bcStopWords.unpersist()
  }

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("lyh")
    val sc = new SparkContext(conf)
    segment(sc)
  }
}
