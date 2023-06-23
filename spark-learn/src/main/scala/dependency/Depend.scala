package dependency

object Depend {

  def main(args: Array[String]): Unit = {
    val sc = getSc()
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))

    // 依赖关系
    val mapRdd = rdd.map(_ + 3)
    // OneToOneDependency 窄依赖 (NarrowDependency)
    // 新 rdd 的一个分区数据依赖旧的 rdd 的一个分区
    println(mapRdd.dependencies)
    println("--------------------------------")

    val groupRdd = mapRdd.groupBy(_ % 2 == 0)
    // ShuffleDependency
    // 新 rdd 的一个分区数据依赖旧的 rdd 的多个分区
    // 上游 rdd 的一个分区被下游的多个分区共享
    println(groupRdd.dependencies)
    println("--------------------------------")

    // rdd 中存在 shuffle 操作时会 Stage 自动增加一个，最后有一个
    groupRdd.collect().foreach(println)

    // 1 -> n 的关系
    // Application, Job, Stage, Task
    // Application: 初始化一个 SparkApplication 生成一个 Application
    // Job: 一个 Action 算子生成一个 Job
    // Stage: 宽依赖的个数+1 (shuffle + 1)
    // Task: 一个 Stage 阶段中，最后一个 rdd 分区的个数就是 task 的个数

  }
}
