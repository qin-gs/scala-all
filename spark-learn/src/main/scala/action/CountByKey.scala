package action

object CountByKey {

  def main(args: Array[String]): Unit = {
    val sc = getSc()

    // 行动算子：触发作业
    val rdd1 = sc.makeRDD(List(1, 1, 1, 2, 2, 3, 4), numSlices = 2)
    println(rdd1.countByValue())

    val rdd2 = sc.makeRDD(List(("a", 1), ("a", 2), ("b", 3), ("c", 4)), numSlices = 2)
    println(rdd2.countByKey())

  }
}
