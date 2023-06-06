package rdd

object PartitionOrder {

  def main(args: Array[String]): Unit = {
    val sc = getSc()

    // 计算分区内的数据是一个一个执行的
    // 前一个数据的全部逻辑执行完后，才会执行下一个数据
    // 分区内数据执行有序
    val rdd = sc.makeRDD(List(1, 2, 3, 4), 1)
    val mapRdd = rdd.map(num => {
      println("---" + num)
      num
    })

    val result = mapRdd.map(num => {
      println("+++" + num)
      num
    })

    result.foreach(println)
  }
}
