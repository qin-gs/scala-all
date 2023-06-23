package dependency

object Depend {

  def main(args: Array[String]): Unit = {
    val sc = getSc()
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))

    // 依赖关系
    val mapRdd = rdd.map(_ + 3)
    // OneToOneDependency 窄依赖 (NarrowDependency)
    // 新 rdd 的一个分区数据依赖旧的 rdd 的一个分区
    println(mapRdd.dependencies)
    println("--------------------------------")

    val groupRdd = mapRdd.groupBy(_ % 2 == 0)
    // ShuffleDependency
    // 新 rdd 的一个分区数据依赖旧的 rdd 的多个分区
    // 上游 rdd 的一个分区被下游的多个分区共享
    println(groupRdd.dependencies)
    println("--------------------------------")

    groupRdd.collect().foreach(println)

  }
}
