package wc

import org.apache.spark.{SparkConf, SparkContext}

object WorkCount {

  def main(args: Array[String]): Unit = {

    // 建立和 spark 框架的连接
    val sparkConf = new SparkConf().setMaster("local").setAppName("wc")
    val sc = new SparkContext(sparkConf)

    // 执行业务操作
    sc.textFile("data")
      .flatMap(_.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
      .foreach(println)

    // 关闭连接
    sc.stop()
  }
}
