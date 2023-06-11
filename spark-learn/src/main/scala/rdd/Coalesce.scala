package rdd

object Coalesce {

  def main(args: Array[String]): Unit = {
    val sc = getSc()
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6), 3)
    // 缩减分区，默认不会打乱分区数据 (shuffle)
    val coalesce = rdd.coalesce(2)
    coalesce.saveAsTextFile("coalesce")

    sc.stop()
  }
}
