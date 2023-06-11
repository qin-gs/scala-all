package rdd

import org.apache.spark.rdd.RDD

object GroupReduce {

  def main(args: Array[String]): Unit = {
    val sc = getSc()
    val rdd = sc.makeRDD(List(("a", 1), ("a", 2), ("b", 3), ("b", 3), ("c", 5), ("d", 6)))

    // group 分组
    val groupBy: RDD[(Int, Iterable[(String, Int)])] = rdd.groupBy(_._2)
    groupBy.foreach(println)

    println("--------------------------------")

    // groupByKey 会打乱数据进行重组(shuffle)
    // 等所有数据分组完成后才能进行下一步
    // shuffle 操作需要写入磁盘，否则会内存溢出
    val groupByKey: RDD[(String, Iterable[Int])] = rdd.groupByKey()
    groupByKey.foreach(x => {
      println(x._1)
      x._2.foreach(println)
    })

    println("--------------------------------")
    // reduceByKey 按 key 分组后聚合 (分区内 和 分区间操作是相同的)
    // 会先对同一个分区的数据进行预处理
    // 写入磁盘的数据少
    // 如果 key 只有一个，不会参与 reduce
    val reduce = rdd.reduceByKey(_ + _)
    reduce.foreach(println)

    sc.stop()
  }
}
