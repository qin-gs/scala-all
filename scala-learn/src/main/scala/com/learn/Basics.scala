package com.learn

import scala.util.Random

object Basics {

  def main(args: Array[String]): Unit = {
    println("Basic")

    // 常量(values)，不能再赋值
    val a = 2 * 4
    println(a)

    // 声明变量类型
    val b: Int = 3 + 5
    println(b)

    // 变量
    var c = 3 - 5
    println(c)
    c = c * 3
    println(c)

    var d: Int = c + 3
    println(d)

    // 代码块 block
    println({
      val a = Random.nextInt(10)
      // 最后一个表达式的结果被返回
      a * 2
    })

    // 匿名函数
    // (x: Int) => x * 2;

    // 命名函数
    val doubleNum = (x: Int) => x * 2
    println(doubleNum(7))

    // 多个参数
    val addFunction = (x: Int, y: Int) => x + y
    println(addFunction(a, c))

    // 方法
    // def 方法名 参数列表 参数类型 返回值 方法体
    def addMethod(a: Int, b: Int): Int = (a + b)

    // 接收多个参数列表
    def addAndMultiply(a: Int, b: Int)(x: Int): Int = (a + b) * x

    println(addAndMultiply(2, 7)(3))

    // 没有参数列表
    def name: String = System.getProperty("user.name")

    println(name)

    def getArea(x: Double): String = {
      val square = x * x
      // 最后一个表达式就是方法的返回值
      square.toString
    }

    println(getArea(2.5))

    // 类
    class Greeter(prefix: String, suffix: String) {
      def greet(name: String): Unit = {
        println(prefix + name + suffix)
      }
    }
    val greeter = new Greeter("hello ", " !")
    greeter.greet("qqq")

    // 样例类
    // 一般用于不可变对象，并且可作值比较
    case class Point(x: Int, y: Int)
    val pointA = Point(2, 4)
    val pointB = Point(1, 5)
    val pointC = Point(3, 4)
    if (pointA == pointB) {
      println(s"$pointA and $pointB are same.")
    } else {
      println(s"$pointA and $pointB are different.")
    }


    // 对象
    // 对象是自己定义的单实例
    object IdFactory {
      private var counter = 0

      def create(): Int = {
        counter += 1
        counter
      }
    }

    val newId = IdFactory.create()
    println(newId)
    val newerId = IdFactory.create()
    println(newerId)

    // 特质
    // 特质是包含某些字段和方法的类型
    trait Hello {
      def hi(name: String): Unit = {
        println("hi " + name + " !")
      }
    }

    // 继承特质
    class DefaultHI extends Hello
    class CustomizableHello(prefix: String, suffix: String) extends Hello {
      override def hi(name: String): Unit = {
        println(prefix + name + suffix)
      }
    }

    val aHi = new DefaultHI()
    aHi.hi("learner")

    val customHi = new CustomizableHello("how are you, ", " ?")
    customHi.hi(System.getProperty("user.name"))

  }


}
