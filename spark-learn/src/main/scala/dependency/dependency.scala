import org.apache.spark.{SparkConf, SparkContext}

package object dependency {

  def getSc(): SparkContext = {
    val conf = new SparkConf().setMaster("local[*]")
      .setAppName("dependency")
    new SparkContext(conf)
  }
}
