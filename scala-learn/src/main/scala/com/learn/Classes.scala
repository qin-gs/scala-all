package com.learn

object Classes {

  def main(args: Array[String]): Unit = {

    // 类包含了方法、常量、变量、类型、对象、特质、类，这些统称为成员
    // 构造器可以提供默认值
    class Point(var x: Int = 3, var y: Int) {

      def move(dx: Int, dy: Int): Unit = {
        x += dx
        y += dy
      }

      override def toString: String = {
        s"($x, $y)"
      }
    }

    var pA = new Point(2, 3)
    println(pA.x)
    println(pA)

    // 只传第二个参数，需要指定参数名
    var pB = new Point(y = 1)
    println(pB)

    class P {
      private var _x = 0
      private var _y = 0
      private val bound = 100

      private def printWarning = println("warning: out of bound")

      // getter
      def x = _x

      // setter
      // 在getter方法的后面加上 _=
      def x_=(newX: Int): Unit = {
        if (newX < bound) {
          _x = newX
        } else {
          printWarning
        }
      }

      def y = _y

      def y_=(newY: Int): Unit = {
        if (newY < bound) {
          _y = newY
        } else {
          printWarning
        }
      }
    }
    val pp = new P
    pp.x = 7
    pp.y = -3
  }
}
