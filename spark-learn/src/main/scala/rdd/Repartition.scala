package rdd

object Repartition {

  def main(args: Array[String]): Unit = {
    val sc = getSc()

    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8), 2)
    // 增加分区，需要 shuffle=true，否则不会拆分区
    val repartition = rdd.repartition(3)
    repartition.saveAsTextFile("repartition")

    sc.stop()
  }
}
