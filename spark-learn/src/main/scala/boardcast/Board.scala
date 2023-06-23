package boardcast

import scala.collection.mutable


object Board {

  def main(args: Array[String]): Unit = {
    val sc = getSc()

    // 广播变量
    val rdd1 = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3)))
    val map = mutable.Map(("a", 4), ("b", 4), ("c", 6))

    // 广播变量可以将闭包中的数据保存到 Executor 内存中
    // 不能修改 (分布式共享只读变量)
    val bc = sc.broadcast(map)

    rdd1.map {
      case (w, c) => {
        // 访问广播变量
        val newCount = bc.value.getOrElse(w, 0) + 1
        (w, (c, newCount))
      }
    }.collect().foreach(println)

    sc.stop()
  }
}
