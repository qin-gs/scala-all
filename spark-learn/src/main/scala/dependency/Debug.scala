package dependency

object Debug {

  def main(args: Array[String]): Unit = {
    val sc = getSc()
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))

    // 血缘关系
    val mapRdd = rdd.map(_ + 3)
    println(mapRdd.toDebugString)
    println("--------------------------------")

    val groupRdd = mapRdd.groupBy(_ % 2 == 0)
    println(groupRdd.toDebugString)
    println("--------------------------------")

    groupRdd.collect().foreach(println)


  }
}
