package word2vec

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.feature.Word2Vec

/**
  * @author yuhanli
  * @date 2019/10/8 3:57 PM
  */
object word2vec {
  def word2VecRun(sc:SparkContext)={
    val input = sc.textFile("/Users/JackKing/Desktop/news_cut.txt").map(line => line.split(" ").toSeq)
    //model train
    val word2vec = new Word2Vec()
      .setVectorSize(50)
      .setNumPartitions(64)

    val model = word2vec.fit(input)
    println("size:" + model.getVectors.size)

    //save and load model
    model.save(sc, "news.model")
    val local = model.getVectors.map{
      case (word, vector) => Seq(word, vector.mkString(" ")).mkString(":")
    }.toArray
    sc.parallelize(local).saveAsTextFile("news_vector.model")

    //predict similar words
    val like = model.findSynonyms("中国", 40)
    for((item, cos) <- like){
      println(s"$item $cos")
    }

  }

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("lyh")
    val sc = new SparkContext(conf)
    word2VecRun(sc)
  }
}
