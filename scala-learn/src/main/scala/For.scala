import scala.collection.immutable
object For {

  case class User(name: String, age: Int)

  def main(args: Array[String]): Unit = {
    val userBase = List(User("q", 26), User("w", 13))
    // 序列推导
    val twenty =
      for (user <- userBase if (user.age >= 20 && user.age <= 30))
        yield user.name
    twenty.foreach(name => println(name))

    foo(10, 10) foreach {
      case (i, j) => println(s"($i, $j)")
    }
  }

  def foo(n: Int, v: Int): immutable.Seq[(Int, Int)] =
    for (i <- 0 until n;
         j <- i until n if i + j == v)
    yield (i, j)

}
