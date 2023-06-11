package rdd

object Fold {

  def main(args: Array[String]): Unit = {
    val sc = getSc()
    val rdd = sc.makeRDD(List(("a", 1), ("a", 2), ("b", 3), ("b", 4), ("b", 5), ("a", 6)), 2)

    // 分区内 和 分区间 操作相同
    rdd.foldByKey(0)(_+_).collect().foreach(println)

    sc.stop()
  }
}
