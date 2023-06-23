package action

object Foreach {

  def main(args: Array[String]): Unit = {
    val sc = getSc()

    // 行动算子：触发作业
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), numSlices = 2)

    // collect 以分区为单位进行聚合
    // 是 Driver 端内存集合的循环遍历方法
    rdd.collect().foreach(println)

    println("--------------------------------")

    val user = new User()
    // foreach Executor 端内存数据打印
    // user 需要实现序列化
    rdd.foreach(num => {
      println(user.age + num)
    })

  }

  // 样例类在编译时会自动混入序列化接口
  case class User() {
    var age: Int = 30
  }
}
