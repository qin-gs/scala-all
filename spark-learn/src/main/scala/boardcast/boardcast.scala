import org.apache.spark.{SparkConf, SparkContext}

package object boardcast {

  def getSc(): SparkContext = {
    val conf = new SparkConf().setMaster("local[*]")
      .setAppName("boardcast")
    new SparkContext(conf)
  }
}
