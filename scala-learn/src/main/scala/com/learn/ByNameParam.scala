package com.learn

import scala.annotation.tailrec

/**
 * 传名参数: 仅在被使用时触发实际参数的求值运算
 */
object ByNameParam {

  /**
   * 使用多个参数列表来分别获取循环条件和循环体
   * 如果 condition = false，就不会计算 body
   */
  @tailrec
  def whileLoop(condition: => Boolean)(body: => Unit): Unit =
    if (condition) {
      body
      whileLoop(condition)(body)
    }

  def main(args: Array[String]): Unit = {
    var i = 2
    whileLoop(i > 0) {
      println(i)
      i -= 1
    }
  }
}
