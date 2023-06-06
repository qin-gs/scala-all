package rdd

import org.apache.spark.rdd.RDD

object Glom {

  def main(args: Array[String]): Unit = {
    val sc = getSc()
    // 将同一个分区的数据转换成相同类型的内存数组进行处理，分区不变

    // 各分区的最大值求和
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19), 4)
    val glomRdd: RDD[Array[Int]] = rdd.glom()
    val maxRdd: RDD[Int] = glomRdd.map(array => {
      array.max
    })
    println(maxRdd.sum())

  }
}
