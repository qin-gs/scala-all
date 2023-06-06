package rdd

object MapPartition {

  def main(args: Array[String]): Unit = {
    val sc = getSc()

    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8), 2)

    // 以分区为单位进行转换
    // 将整个分的数据加载到内存中进行处理，处理完后不会释放

    // 获取每个分区的最大值
    val mapRdd = rdd.mapPartitions(iter => {
      println("---")
      List(iter.max).iterator
    })
    mapRdd.collect().foreach(println)
  }
}
