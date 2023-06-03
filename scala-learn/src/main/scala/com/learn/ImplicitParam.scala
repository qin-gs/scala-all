package com.learn

/**
 * 隐式参数
 */
object ImplicitParam {

  abstract class Monoid[A] {
    def add(x: A, y: A): A

    def unit: A
  }

  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    override def add(x: String, y: String): String = x concat y

    override def unit: String = ""
  }

  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
    override def add(x: Int, y: Int): Int = x + y

    override def unit: Int = 0
  }

  /**
   * 该方法有一个 隐式参数列表
   *
   * Scala 在调用包含有隐式参数块的方法时，将首先查找可以直接访问的隐式定义和隐式参数 (无前缀)
   * 然后，它在所有伴生对象中查找与隐式候选类型相关的有隐式标记的成员
   */
  def sum[A](xs: List[A])(implicit m: Monoid[A]): A =
    if (xs.isEmpty) {
      m.unit
    } else {
      m.add(xs.head, sum(xs.tail))
    }

  def main(args: Array[String]): Unit = {
    println(sum(List(1, 2, 3))) // 寻找 Monoid[Int]
    println(sum(List("a", "b", "c")))
  }
}
