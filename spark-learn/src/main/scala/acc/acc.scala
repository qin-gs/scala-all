import org.apache.spark.{SparkConf, SparkContext}

package object acc {

  def getSc(): SparkContext = {
    val conf = new SparkConf().setMaster("local[*]")
      .setAppName("acc")
    new SparkContext(conf)

  }
}
