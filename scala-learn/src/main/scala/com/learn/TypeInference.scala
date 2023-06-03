package com.learn

/**
 * 类型推断
 */
object TypeInference {

  val businessName = "Apple"

  /**
   * 可以自动推断出返回 Int
   */
  def squareOf(x: Int): Int = x * x

  /**
   * 递归无法省略类型
   */
  def fac(n: Int): Int =
    if (n == 0) {
      1
    } else {
      n * fac(n - 1)
    }

}
