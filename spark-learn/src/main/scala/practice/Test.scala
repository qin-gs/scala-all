package practice

object Test {

  def main(args: Array[String]): Unit = {
    val sc = getSc()

    // 获取数据
    // 时间 省份 城市 用户 广告
    // 每个省份被点击数量排行 top3
    sc.textFile("data/agent.log")
      .map(x => {
        val strings = x.split(" ")
        // ((prov, ad), num)
        ((strings(1), strings(2)), 1)
      }).reduceByKey(_ + _) // 将 value 求和
      .map {
        case ((prov, ad), sum) => {
          (prov, (ad, sum))
        }
      }.groupByKey()
      .mapValues(x => x.toList.sortBy(_._2)(Ordering.Int.reverse).take(3))
      .foreach(println)
  }
}
