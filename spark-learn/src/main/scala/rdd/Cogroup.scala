package rdd

object Cogroup {

  def main(args: Array[String]): Unit = {
    val sc = getSc()

    val rdd1 = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3)))
    val rdd2 = sc.makeRDD(List(("a", 4), ("b", 5), ("a", 6), ("d", 7)))

    // 分组连接
    rdd1.cogroup(rdd2).foreach(println)
  }
}
