import org.apache.spark.SparkConf
import org.apache.spark.streaming._

object QuickStart {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    val ssc = new StreamingContext(conf, Seconds(5))

    val lines = ssc.socketTextStream("localhost", 9999)
    val words = lines.flatMap(_.split(" "))

    val paris = words.map(word => (word, 1))

    paris.updateStateByKey(updateFunc)
    paris.reduceByKeyAndWindow((a: Int, b: Int) => (a + b), Seconds(30), Seconds(10))

    val wordCounts = paris.reduceByKey(_ + _)
    wordCounts.print()

    ssc.start()
    ssc.awaitTermination()
  }

  def updateFunc(newValues: Seq[Int], state: Option[Int]): Option[Int] = {
    var newCount = state.getOrElse(0)
    for (elem <- newValues) {
      newCount += elem
    }
    Some(newCount)
  }
}
