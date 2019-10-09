package word2vec

import org.apache.spark.SparkContext
import org.apache.spark.mllib.feature.Word2Vec

/**
  * @author yuhanli
  * @date 2019/10/8 3:57 PM
  */
object word2vec {
  def word2VecRun(sc:SparkContext)={
    val input = sc.textFile("分词结果路径").map(line => line.split(" ").toSeq)
    //model train
    val word2vec = new Word2Vec()
      .setVectorSize(50)
      .setNumPartitions(64)

    val model = word2vec.fit(input)
    println("size:" + model.getVectors.size)

    //save and load model
    model.save(sc, "word2vec模型路径")
    val local = model.getVectors.map{
      case (word, vector) => Seq(word, vector.mkString(" ")).mkString(":")
    }.toArray
    sc.parallelize(local).saveAsTextFile("word2vec词向量路径")

    //predict similar words
    val like = model.findSynonyms("中国", 40)
    for((item, cos) <- like){
      println(s"$item $cos")
    }


  }

  def main(args: Array[String]): Unit = {

  }
}
