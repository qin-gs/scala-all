import org.apache.spark.{SparkConf, SparkContext}

package object action {

  def getSc(): SparkContext = {
    val conf = new SparkConf().setMaster("local[*]")
      .setAppName("action")
    new SparkContext(conf)
  }
}
