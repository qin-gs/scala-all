package persist

import org.apache.spark.storage.StorageLevel

object Cache {

  def main(args: Array[String]): Unit = {
    val sc = getSc()
    sc.setCheckpointDir("checkpoint")

    val rdd = sc.makeRDD(List("hello world", ("hello scala")))
    val flatMap = rdd.flatMap(_.split(" "))
    val map = flatMap.map(word => {
      println("map --------------------------------")
      (word, 1)
    })

    // 在内存中缓存上一步的操作
    // 在血缘关系中添加新的依赖，出现问题后从头读取
    // 如果不进行缓存，会再次从头进行数据操作
    // map.cache()

    // 持久化操作在行动算子执行时完成，执行完成后删除
    map.persist(StorageLevel.DISK_ONLY)

    // 检查点：存入磁盘，有 IO 操作，完成后不删除
    // 执行过程中会切断血缘关系，然后重新建立
    map.checkpoint()

    map.reduceByKey(_ + _).collect().foreach(println)
    map.groupByKey().collect().foreach(println)

    sc.stop()
  }

}
