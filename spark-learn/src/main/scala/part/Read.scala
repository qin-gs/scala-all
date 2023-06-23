package part

object Read {

  def main(args: Array[String]): Unit = {
    val sc = getSc()

    val rdd = sc.makeRDD(List(("a", "this is a"), ("b", "this is b"), ("c", "this is c"), ("d", "this is d")))

    // 保存数据
    rdd.saveAsTextFile("saveAsTextFile")
    rdd.saveAsObjectFile("saveAsObjectFile")
    // 只能保存 kv 结构的数据
    rdd.saveAsSequenceFile("saveAsSequenceFile")

    // 读取数据
    sc.textFile("saveAsTextFile")
    sc.objectFile[(String, String)]("saveAsObjectFile")
    sc.sequenceFile[String, String]("saveAsSequenceFile")

  }
}
