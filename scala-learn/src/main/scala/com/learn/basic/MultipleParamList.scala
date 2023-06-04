package com.learn.basic

/**
 * 多参数列表: 传递匿名函数作为参数
 */
object MultipleParamList {


  def main(args: Array[String]): Unit = {
    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val res = numbers.foldLeft(0)((m, n) => m + n)
    println(res)

    // 使用类型推断
    numbers.foldLeft(0)(_ + _)

    val numFunc = numbers.foldLeft(List[Int]()) _
    val squares = numFunc((xs, x) => xs :+ x * x)
    println(squares)

    val cubes = numFunc((xs, x) => xs :+ x * x * x)
    println(cubes)


  }

}
