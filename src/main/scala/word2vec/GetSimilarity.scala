package word2vec

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.feature.{Word2Vec, Word2VecModel}

/**
  * @author yuhanli
  * @date 2019/10/10 9:37 AM
  */
object GetSimilarity {

  //寻找对于字符串s，相似度最高的前num个string
  def getSimilarity(sc: SparkContext, s: String, num: Int) ={
    val model = Word2VecModel.load(sc, "./news.model")
    val sd = model.findSynonyms(s, num = 50)
    for(s <- sd){
      println(s._1 + "      " + s._2)
    }
  }

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("lyh")
    val sc = new SparkContext(conf)
    getSimilarity(sc, "山东", 50)
  }
}
