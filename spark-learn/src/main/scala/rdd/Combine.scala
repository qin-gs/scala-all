package rdd

object Combine {

  def main(args: Array[String]): Unit = {
    val sc = getSc()
    val rdd = sc.makeRDD(List(("a", 1), ("a", 2), ("b", 3), ("b", 4), ("b", 5), ("a", 6)), 2)

    // 三个参数
    // 1. 相同 key 的第一个数据进行的结构转换
    // 2. 分区内计算规则
    // 3. 分区间计算规则
    rdd.combineByKey(
      (_, 1),
      (t: (Int, Int), v) => {
        (t._1 + v, t._2 + 1)
      },
      (t1: (Int, Int), t2: (Int, Int)) => {
        (t1._1 + t2._1, t1._2 + t2._2)
      }).mapValues {
      case (num, cnt) => num / cnt
    }.foreach(println)


    sc.stop()
  }
}
