import org.apache.spark.sql.SparkSession

object Start {

  def main(args: Array[String]): Unit = {

    val session = SparkSession.builder()
      .master("local[*]")
      .appName("Spark Sql Basic example")
      .config("spark.sql.warehouse.dir", "target/spark-warehouse")
      .getOrCreate()

    runBasicDataFrameExample(session)
  }

  def runBasicDataFrameExample(spark: SparkSession): Unit = {
    import spark.implicits._

    val df = spark.read.json("./spark-sql/src/main/resources/people.json")
    df.show()

    df.printSchema()

    df.select("name").show()

    df.select($"name", $"age" + 1).show()
  }


}
