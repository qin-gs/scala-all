package rdd

object Aggregate {

  def main(args: Array[String]): Unit = {
    val sc = getSc()
    val rdd = sc.makeRDD(List(("a", 1), ("a", 2), ("b", 3), ("b", 4), ("b", 5), ("a", 6)), 2)

    // 分区内 和 分区间 操作不同
    // 分区内求最大值，分区间求和
    // 第一个参数：初始值
    // 第二个参数：
    //   分区内计算规则 (x, y) => math.max(x, y)
    //   分区间计算规则 (x, y) => x + y
    rdd.aggregateByKey(0)((x, y) => math.max(x, y), (x, y) => x + y)
      .collect().foreach(println)

    // val aggregate = rdd.aggregateByKey(0)(_ + _, _ + _)

    // 两个分区
    // ("a", 1), ("a", 2), ("b", 3) 组内求最大 ("a", 2), ("b", 3) 组间求和，先 shuffle ("a", 2), ("a", 6) 结果 ("a", 8)
    // ("b", 4), ("b", 5), ("a", 6) 组内求最大 ("b", 5), ("a", 6)                    ("b", 3), ("b", 5)     ("b", 8)

    // 求相同 key 的平均值 (求和的值, 求和次数)
    rdd.aggregateByKey((0, 0))((t, v) => (t._1 + v, t._2 + 1), (t1, t2) => (t1._1 + t2._1, t2._2 + t2._2))
      .mapValues {
        case (num, cnt) => num / cnt
      }.collect().foreach(println)

    sc.stop()
  }
}
