package rdd

import org.apache.spark.{SparkConf, SparkContext}

object RddPartition {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("create rdd in memory")
    val sc = new SparkContext(conf)

    // 配置分区数量
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 2)
    rdd.saveAsTextFile("output")

    sc.stop()

  }
}
