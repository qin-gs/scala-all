import org.apache.spark.{SparkConf, SparkContext}

package object part {

  def getSc(): SparkContext = {
    val conf = new SparkConf().setMaster("local[*]")
      .setAppName("part")
    new SparkContext(conf)
  }
}
