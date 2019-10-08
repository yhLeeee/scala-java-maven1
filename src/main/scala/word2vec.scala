import org.apache.spark.SparkContext
import org.apache.spark.mllib.feature.Word2Vec

/**
  * @author jinxianbao
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
    
  }
}
