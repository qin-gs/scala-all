package acc

object Sum {

  def main(args: Array[String]): Unit = {
    val sc = getSc()

    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5))

    // 获取系统的累加器
    val sumAcc = sc.longAccumulator("sum")
    rdd.foreach(num => {
      sumAcc.add(num)
    })

    // 累加器一般使用在行动算子中
    // 少加：转换算子中调用累加器，如果没有行动算子会少加
    // 多加：调用多次行动算子会多加
    println(sumAcc.value)

    sc.stop()
  }
}
