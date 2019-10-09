package word2vec

import com.hankcs.hanlp.tokenizer.NLPTokenizer
import org.apache.spark.SparkContext

/**
  * @author yuhanli
  * @date 2019/10/8 3:40 PM
  */
object SeparateWord {
  def segment(sc:SparkContext): Unit ={
    //stop words
    val stopWordPath = "停用词路径"
    val bcStopWords = sc.broadcast(sc.textFile(stopWordPath).collect().toSet)

    //segment
    val inPath = "训练语料路径"
    val segmentRes = sc.textFile(inPath)
      .map(AsciiUtil2.sbc2dbcCase(_)) //全角转半角
      .mapPartitions(it => {
      it.map(ct => {
        try{
          val nlpList = NLPTokenizer.segment(ct)
          nlpList.asScala.map(term => term.word)
            .filter(!bcStopWords.value.contains(_))
            .mkString(" ")
        }catch {
          case e:NullPointerException => println(e);""
        }
      })
    })

    //save segment result
    segmentRes.saveAsTextFile("分词结果路径")
    bcStopWords.unpersist()
  }
}
