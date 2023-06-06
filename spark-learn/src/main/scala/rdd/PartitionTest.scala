package rdd

object PartitionTest {

  def main(args: Array[String]): Unit = {
    val sc = getSc()

    // 按行读取
    // 每个分区存放 totalSize / numSplits 个字节
    // 分区数量 = totalSize / 每个分区字节
    val lines = sc.textFile("data/num.txt", minPartitions = 2)
    lines.saveAsTextFile("output")

    sc.stop()
  }

}
