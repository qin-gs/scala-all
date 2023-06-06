package rdd

object RddCreate {

  def main(args: Array[String]): Unit = {
    val sc = getSc()

    // 从内存中创建 rdd，底层使用 parallelize 方法创建
    val seq = Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val rdd = sc.makeRDD(seq)
    rdd.foreach(println)

    // 从文件中创建 rdd
    // 传入 文件名 或 路径，以行为单位读取 rdd
    // 可以使用通配符
    val lines = sc.textFile("data/*.txt")
    lines.collect().foreach(println)

    // 以文件为单位读取
    val wholeLines = sc.wholeTextFiles("data")
    wholeLines.collect().foreach(println)

    sc.stop()
  }
}
