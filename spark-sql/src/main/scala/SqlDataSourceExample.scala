import org.apache.spark.sql.SparkSession

object SqlDataSourceExample {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[*]")
      .appName("Spark SQL data sources example")
      .getOrCreate()

    runBasicDataSourceExample(spark)
  }

  def runBasicDataSourceExample(spark: SparkSession): Unit = {
    val userDf = spark.read.load("./spark-sql/src/main/resources/users.parquet")
    userDf.select("name", "favorite_color").write.save("namesAndFavColors.parquet")

    spark.read.format("csv")
      .option("sep", ";")
      .option("inferSchema", "true")
      .option("header", "true")
      .load("./spark-sql/src/main/resources/people.csv")

    spark.sql("select * from parquet.`./spark-sql/src/main/resources/users.parquet`")
  }
}
