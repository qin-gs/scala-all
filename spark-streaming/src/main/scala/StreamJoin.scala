import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

import java.sql.Connection

object StreamJoin {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    val ssc = new StreamingContext(conf, Seconds(5))

    val lines1: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)

    lines1.foreachRDD { rdd => {
      rdd.foreachPartition(partition => {
        // 在每个分区中单独创建数据库连接
        val connection: Connection = null
        // 对分区的每一条数据进行操作
        partition.foreach(record => {
          // 这里使用 connection
        })
      })
    }
    }

  }
}
