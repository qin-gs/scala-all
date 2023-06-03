/**
 * 高阶函数: 使用函数值作为参数，或者返回值为函数值的“函数”和“方法”，均称之为“高阶函数”
 */
object HigherFunc {
  def main(args: Array[String]): Unit = {
    val salaries = Seq(2000, 7000, 40000)
    val doubleSalary = (x: Int) => x * 2
    val newSalaries = salaries.map(doubleSalary)
    // 自动推断 x 为 Int
    val annoySalaries = salaries.map(x => x * 2)
    // 用 _ 替代参数名
    val annoyNewSalaries = salaries.map(_ * 2)

    println(newSalaries)
    println(annoySalaries)
    println(annoyNewSalaries)

    val domainName = "www.example.com"

    def getUrl = urlBuilder(ssl = true, domainName)

    val endpoint = "users"
    val query = "name=qqq"
    val url = getUrl(endpoint, query)
    println(url)
  }

  case class WeeklyWeatherForecast(temperatures: Seq[Double]) {
    private def convertCtoF(temp: Double) = temp * 1.8 + 32

    /**
     * 传入一个对象方法作为高阶函数的参数，Scala 编译器会将方法强制转换为一个函数
     */
    def forecastInFahrenheit: Seq[Double] = temperatures.map(convertCtoF)
  }

  object SalaryRaiser {
    def smallPromotion(salaries: List[Double]): List[Double] =
      salaries.map(salary => salary * 1.1)

    def greatPromotion(salaries: List[Double]): List[Double] =
      salaries.map(salary => salary * math.log(salary))

    def hugePromotion(salaries: List[Double]): List[Double] =
      salaries.map(salary => salary * salary)
  }

  object SalaryRaiserAdvanced {
    private def promotion(salaries: List[Double], promotionFunc: Double => Double) =
      salaries.map(promotionFunc)

    def smallPromotion(salaries: List[Double]): List[Double] =
      promotion(salaries, salary => salary * 1.1)
  }

  /**
   * 返回函数的函数
   */
  def urlBuilder(ssl: Boolean, domainName: String): (String, String) => String = {
    val schema = if (ssl) "https://" else "http://"
    (endpoint: String, query: String) => s"$schema$domainName/$endpoint?$query"
  }
}
