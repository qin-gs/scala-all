package com.learn.basic

/**
 * 嵌套方法
 */
object NestedFunc {

  def main(args: Array[String]): Unit = {
    println(factorial(6))
  }

  def factorial(x: Int): Int = {
    def fact(x: Int, accumulator: Int): Int = {
      if (x <= 1) {
        accumulator
      } else {
        fact(x - 1, x * accumulator)
      }
    }

    fact(x, 1)
  }

}
