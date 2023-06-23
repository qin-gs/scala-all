package rdd

object Join {

  def main(args: Array[String]): Unit = {
    val sc = getSc()

    val rdd1 = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3)))
    val rdd2 = sc.makeRDD(List(("a", 4), ("b", 5), ("a", 6)))

    // join: 相同 key 的 value 组合起来
    // key 有多个相同的，会依次匹配
    // key 没有相同的，不会出现在结果中
    rdd1.join(rdd2).collect().foreach(println)

    // 左连接
    rdd1.leftOuterJoin(rdd2).foreach(println)


    sc.stop()
  }
}
