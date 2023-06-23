package action

object ActionFunc {

  def main(args: Array[String]): Unit = {
    val sc = getSc()
    // 行动算子：触发作业
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))

    println(rdd.count())
    println(rdd.first())
    println(rdd.take(3).mkString(", "))
    // 数据排序后取 n 个
    println(rdd.takeOrdered(5)(Ordering.Int.reverse).mkString(" -> "))

    sc.stop()
  }
}
