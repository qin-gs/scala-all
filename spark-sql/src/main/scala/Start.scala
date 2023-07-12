import org.apache.spark.sql.{Encoder, SparkSession}

object Start {

  def main(args: Array[String]): Unit = {

    val session = SparkSession.builder()
      .master("local[*]")
      .appName("Spark Sql Basic example")
      .config("spark.sql.warehouse.dir", "target/spark-warehouse")
      .getOrCreate()

    // runBasicDataFrameExample(session)
    // runDatasetCreationExample(session)
    // runInferSchemaExample(session)
    runProgrammaticSchemaExample(session)
  }

  def runBasicDataFrameExample(spark: SparkSession): Unit = {
    import spark.implicits._

    val df = spark.read.json("./spark-sql/src/main/resources/people.json")
    df.show()

    df.printSchema()

    df.select("name").show()

    df.select($"name", $"age" + 1).show()

    df.filter($"age" > 21).show()

    df.groupBy("age").count().show()

    // 临时视图
    df.createOrReplaceTempView("people")
    val sqlDf = spark.sql("select * from people")
    sqlDf.show()
  }

  case class Person(name: String, age: Long)

  private def runDatasetCreationExample(spark: SparkSession): Unit = {
    import spark.implicits._

    // 数据源
    val caseClassDs = Seq(Person("Andy", 32)).toDS()
    caseClassDs.show()

    val primitiveDs = Seq(1, 2, 3).toDS()
    primitiveDs.map(_ + 1).collect()

    val path = "./spark-sql/src/main/resources/people.json"
    val peopleDs = spark.read.json(path).as[Person]
    peopleDs.show()
  }

  private def runInferSchemaExample(spark: SparkSession): Unit = {

    import spark.implicits._

    val peopleDf = spark.sparkContext
      .textFile("./spark-sql/src/main/resources/people.txt")
      .map(_.split(","))
      .map(attributes => Person(attributes(0), attributes(1).trim.toInt))
      .toDF()
    peopleDf.createOrReplaceTempView("people")

    val teenagerDf = spark.sql("select name, age from people where age between 13 and 19")
    teenagerDf.map(teenager => "Name: " + teenager(0)).show()
    teenagerDf.map(teenager => "Name: " + teenager.getAs[String]("name")).show()

    implicit val mapEncoder: Encoder[Map[String, Any]] = org.apache.spark.sql.Encoders.kryo[Map[String, Any]]
    teenagerDf.map(teenager => teenager.getValuesMap[Any](List("name", "age"))).collect()
  }

  private def runProgrammaticSchemaExample(spark: SparkSession): Unit = {
    import spark.implicits._
    import org.apache.spark.sql.Row
    import org.apache.spark.sql.types._

    val peopleRdd = spark.sparkContext.textFile("./spark-sql/src/main/resources/people.txt")
    val schemaString = "name age"
    val fields = schemaString.split(" ")
      .map(fieldName => StructField(fieldName, StringType, nullable = true))
    val schema = StructType(fields)

    val rowRdd = peopleRdd.map(_.split(","))
      .map(attributes => Row(attributes(0), attributes(1).trim))

    val peopleDf = spark.createDataFrame(rowRdd, schema)
    peopleDf.createOrReplaceTempView("people")

    val results = spark.sql("select name from people")
    results.map(attributes => "Name: " + attributes(0)).show()

  }


}
