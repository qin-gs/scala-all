
import org.apache.spark.SparkConf
import org.apache.spark.api.java.StorageLevels
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext, Time}
import org.slf4j.{Logger, LoggerFactory}

object SqlNetworkWordCount {

  protected final val log: Logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("SqlNetworkWordCount")
    val ssc = new StreamingContext(sparkConf, Seconds(2))
    val lines = ssc.socketTextStream("localhost", 9999, StorageLevels.MEMORY_AND_DISK_SER)
    val words = lines.flatMap(_.split(" "))

    words.foreachRDD {
      (rdd: RDD[String], time: Time) => {
        val spark = SparkSessionSingleton.getInstance(rdd.sparkContext.getConf)

        import spark.implicits._

        val wordsDf = rdd.map(w => Record(w)).toDF()
        wordsDf.createOrReplaceTempView("words")
        val wordCount = spark.sql("select word, count(*) from words group by word")
        println(s"--- ${time} ---")

        wordCount.show()
      }
    }

    ssc.start()
    ssc.awaitTermination()
  }


}

case class Record(word: String)

object SparkSessionSingleton {
  @transient private var instance: SparkSession = _

  def getInstance(sparkConf: SparkConf): SparkSession = {
    if (instance == null) {
      instance = SparkSession
        .builder
        .config(sparkConf)
        .getOrCreate()
    }
    instance
  }
}
