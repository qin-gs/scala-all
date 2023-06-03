package com.learn.basic

import com.fasterxml.jackson.databind.ObjectMapper

import java.io.{FileNotFoundException, FileReader, IOException}
import scala.collection.mutable
import scala.io.Source
import scala.language.postfixOps
import scala.util.Random

/**
 * 单例对象
 */
object Main {

  def main(args: Array[String]): Unit = {

    var hello: String = "hello \t world";
    println(hello)

    var i: Int = 3;

    for (j <- 0 until 10) {
      println(j)
    }

    var data: List[Int] = List(1, 2, 3, 4, 5, 6, 7);
    var y = for (a <- data if a % 2 == 0) yield a;


    println(add.addInt(3, 7))

    var sb: mutable.StringBuilder = new mutable.StringBuilder(hello);
    println(sb.length)

    var arr: Array[Char] = Array('h', 'e', 'l', 'l', '0')
    for (i <- arr) {
      println(i)
    }
    var sum: Int = 0
    for (i <- arr) {
      sum += i
    }
    println(sum)

    var it: Iterator[Int] = Iterator(20, 30, 40, 50, 60, 70, 80, 90)
    while (it.hasNext) {
      println(it.next())
    }

    /**
     * switch 语句
     */
    def matchInt(i: Any): String = i match {
      case 0 => "0"
      case 1 => "1"
      case Point(x, y) => x + "-" + y
      // default
      case _ => "many"
    }

    /**
     * 样例类，用于 match 模式匹配
     */
    case class Point(x: Double, y: Double)

    // 构造一个 regex 对象
    var pattern = "\\d".r;
    var str = "there are 5 number in 1 sentence"
    val numIt = pattern findAllIn str
    while (numIt hasNext) {
      println(numIt next)
    }

    /**
     * 捕获异常，使用模式匹配处理
     */
    try {
      var f = new FileReader("aFile.txt")
    } catch {
      case ex: FileNotFoundException => println("找不到文件")
      case ex: IOException => println("io exception")
    } finally {
      println("exit")
    }

    // 从命令行读取
    //    var line = StdIn.readLine()
    //    println(line)

    // 从文件读取
    Source.fromFile("test.txt").foreach(print)

  }
}


object add {
  def addInt(x: Int, y: Int): Int = {
    x + y + Random.nextInt(y)
  }
}

class Point(xc: Int, yc: Int) extends Equal {
  var x: Int = xc
  var y: Int = yc

  def move(dx: Int, dy: Int): Unit = {
    x = x + dx;
    y += dy
    println("x 的坐标")
    println("y 的坐标")
  }

  override def toString: String = super.toString

  override def isEqual(x: Any): Boolean = {
    x.isInstanceOf[Point] && x.asInstanceOf[Point].x == x
  }
}

/**
 * 伴生对象
 */
object Point {
  private val points: Map[String, Point] = Map(
    "a" -> new Point(1, 2),
    "b" -> new Point(3, 4),
    "c" -> new Point(5, 6)
  )

  def apply(p: String): Point = {
    if (points.contains(p)) {
      points(p)
    } else {
      null
    }
  }

  def getPoint(p: String): Point = {
    if (points.contains(p)) {
      points(p)
    } else {
      null
    }
  }

  def main(args: Array[String]): Unit = {
    println(apply("a"))
    println(Point getPoint "a")
    println(toString)
  }

  override def toString: String = {
    val s: String = new ObjectMapper().writeValueAsString(points)
    s
  }

}

class Location(val xc: Int, val yc: Int, var zc: Int) extends Point(xc, yc) {
  var z: Int = zc

  def move(dx: Int, dy: Int, dz: Int): Unit = {
    x = x + dx
    y = y + dy
    z = z + dz
  }

  override def toString: String = {
    "(" + x + ", " + y + ", " + z + ")"
  }
}

/**
 * 特征
 */
trait Equal {
  def isEqual(x: Any): Boolean

  def isNotEqual(x: Any): Boolean = !isEqual(x)
}
