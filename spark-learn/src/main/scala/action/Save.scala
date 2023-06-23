package action

object Save {

  def main(args: Array[String]): Unit = {
    val sc = getSc()

    // 行动算子：触发作业
    val rdd = sc.makeRDD(List(
      ("a", 1), ("b", 2), ("c", 3), ("d", 4)
    ), numSlices = 2)
    rdd.saveAsTextFile("saveAsTextFile")
    rdd.saveAsObjectFile("saveAsObjectFile")

    // 要求 kv 类型
    rdd.saveAsSequenceFile("saveAsSequenceFile")

    sc.stop()
  }
}
