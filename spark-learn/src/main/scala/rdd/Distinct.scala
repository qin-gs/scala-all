package rdd

object Distinct {

  def main(args: Array[String]): Unit = {
    val sc = getSc()

    val rdd = sc.makeRDD(List(1, 2, 3, 4, 1, 2, 3, 4))
    // map(x => (x, null)).reduceByKey((x, _) => x, numPartitions).map(_._1)
    rdd.distinct().foreach(println)
  }
}
