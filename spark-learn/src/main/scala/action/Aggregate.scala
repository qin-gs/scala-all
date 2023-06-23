package action

object Aggregate {

  def main(args: Array[String]): Unit = {
    val sc = getSc()

    // 行动算子：触发作业
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), numSlices = 2)

    // 分区内计算
    // aggregateByKey 初始值参与分区间计算
    // aggregate 初始值参与分区内计算 和 参与分区间计算
    println(rdd.aggregate(5)(_ + _, _ + _))

    // fold 分区内 和 分区间 操作相同
    println(rdd.fold(5)(_ + _))

  }
}
