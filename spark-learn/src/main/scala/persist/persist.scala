import org.apache.spark.{SparkConf, SparkContext}

package object persist {

  def getSc(): SparkContext = {
    val conf = new SparkConf().setMaster("local[*]")
      .setAppName("persist")
    new SparkContext(conf)
  }
}
