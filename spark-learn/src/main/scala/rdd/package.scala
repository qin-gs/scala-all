import org.apache.spark.{SparkConf, SparkContext}

package object rdd {

  def getSc(): SparkContext = {
    val conf = new SparkConf().setMaster("local[*]")
      .setAppName("rdd")
    new SparkContext(conf)
  }
}
