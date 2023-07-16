import org.apache.spark.sql.SparkSession

object SqlDataSourceExample {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[*]")
      .appName("Spark SQL data sources example")
      .getOrCreate()

    // runBasicDataSourceExample(spark)
    // runGenericFileSourceOptionsExample(spark)
    // runCsvDatasetExample(spark)
    // runTextDatasetExample(spark)
    runJdbcDatasetExample(spark)
  }

  def runBasicDataSourceExample(spark: SparkSession): Unit = {
    val userDf = spark.read.load("./spark-sql/src/main/resources/users.parquet")
    userDf.select("name", "favorite_color").write.save("namesAndFavColors.parquet")

    val peopleDf = spark.read.format("csv")
      .option("sep", ";")
      .option("inferSchema", "true")
      .option("header", "true")
      .load("./spark-sql/src/main/resources/people.csv")

    spark.sql("select * from parquet.`./spark-sql/src/main/resources/users.parquet`")
    peopleDf.write.bucketBy(42, "name").mode("overwrite").sortBy("age").saveAsTable("people_bucketed")
    userDf.write.partitionBy("favorite_color").format("parquet").save("namesPartByColor.parquet")
    userDf.write.partitionBy("favorite_color").bucketBy(42, "name").saveAsTable("users_partitioned_bucketed")

    spark.sql("DROP TABLE IF EXISTS people_bucketed")
    spark.sql("DROP TABLE IF EXISTS users_partitioned_bucketed")
  }

  private def runGenericFileSourceOptionsExample(spark: SparkSession): Unit = {
    // 忽略损坏的文件
    // 过滤文件
    // 递归搜索
    // 指定时间
    val testCorruptDf0 = spark.read.option("ignoreCorruptFiles", "true")
      .option("pathGlobFilter", "*.parquet")
      .option("recursiveFileLookup", "true")
      .option("modifiedAfter", "2023-07-16T18:28:12")
      .parquet("./spark-sql/src/main/resources/dir1/", "./spark-sql/src/main/resources/dir1/dir2/")
    testCorruptDf0.show()

    // 两种方式
    spark.sql("set spark.sql.file.ignoreCorruptFiles=true")

    println(spark.time())
  }

  private def runCsvDatasetExample(spark: SparkSession): Unit = {
    var path = "./spark-sql/src/main/resources/people.csv"

    // 可以是 csv 文件的路径，也可以是目录(需要保证目录下全部是 csv 文件)
    val df = spark.read.csv(path)
    df.show()

    val df2 = spark.read.option("delimiter", ";").csv(path)
    df2.show()

    val df3 = spark.read.options(Map("delimiter" -> ";", "header" -> "true"))
      .csv(path)
    df3.show()

    df3.write.csv("output")

  }

  private def runTextDatasetExample(spark: SparkSession): Unit = {
    var path = "./spark-sql/src/main/resources/people.txt"
    val df1 = spark.read.text(path)
    df1.show()

    val df2 = spark.read.option("lineSep", ",").text(path)
    df2.show()

    val df3 = spark.read.option("wholetext", "true").text(path)
    df3.show()

    df1.write.option("compression", "gzip").text("output_compressed")
  }

  private def runJdbcDatasetExample(spark: SparkSession): Unit = {
    val jdbcDf = spark.read
      .format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/springcloud-demo")
      .option("dbtable", "sys_role")
      .option("user", "root")
      .option("password", "password")
      .load()

    jdbcDf.show()

  }
}
