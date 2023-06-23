package part

import org.apache.spark.Partitioner

object Partition {

  def main(args: Array[String]): Unit = {
    val sc = getSc()
    val rdd = sc.makeRDD(List(("a", "this is a"), ("b", "this is b"), ("c", "this is c"), ("d", "this is d")))
    // 分区
    val partition = rdd.partitionBy(new APartitioner)
    partition.saveAsTextFile("partition")
    sc.stop()
  }

  class APartitioner extends Partitioner {
    override def numPartitions: Int = 3

    override def getPartition(key: Any): Int = {
      key match {
        case "a" => 0
        case "b" => 1
        case _ => 2
      }
    }
  }
}
