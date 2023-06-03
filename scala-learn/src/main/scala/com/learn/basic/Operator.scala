package com.learn.basic

/**
 * 运算符即是方法
 */
object Operator {
  def main(args: Array[String]): Unit = {
    println(10.+(3))

    val v1 = Vec(1.0, 2.1)
    val v2 = Vec(1.3, 2.7)
    println(v1.+(v2))
  }

  case class Vec(x: Double, y: Double) {
    def +(that: Vec): Vec = Vec(this.x + that.x, this.y + that.y)
  }

  case class MyBool(x: Boolean) {
    def and(that: MyBool): MyBool =
      if (x) {
        that
      } else {
        this
      }

    def or(that: MyBool): MyBool =
      if (x) {
        this
      } else {
        that
      }

    def negate: MyBool = MyBool(!x)

    def not(x: MyBool): MyBool = x.negate

    def xor(x: MyBool, y: MyBool): MyBool =
      (x or y) and not(x and y)
  }
}
