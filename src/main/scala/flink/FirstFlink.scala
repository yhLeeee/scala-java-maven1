package flink

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time
/**
  * @author yuhanli
  * @date 2019/10/11 4:43 PM
  */
object FirstFlink {
  def main(args: Array[String]): Unit = {
    val inputPath: String = "./src/main/scala/scalaTest/WordCount";
    val outputPath: String = "./src/main/scala/scalaTest/result";
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment;
    val text = env.readTextFile(inputPath)
    import org.apache.flink.api.scala._
    val counts = text.flatMap(line => line.split(" ")).map(word => word+1).groupBy(0).sum(1)
    counts.writeAsCsv(outputPath, "\n", " ")
    env.execute("batch word count")

  }
}
