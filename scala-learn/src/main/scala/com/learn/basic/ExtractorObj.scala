package com.learn.basic

import scala.util.Random

/**
 * 提取器
 */
object ExtractorObj {

  def main(args: Array[String]): Unit = {
    val customerId = ExtractorObj("Sukyoung")
    customerId match {
      case ExtractorObj(name) => println(name)
      case _ => println("could not extract a id")
    }
  }

  // 类似构造器: 接收参数创建一个实例对象
  def apply(name: String) = s"$name==${Random.nextLong()}"

  // 接收一个实例对象返回最初创建时提供的参数
  def unapply(customerId: String): Option[String] = {
    val stringArray: Array[String] = customerId.split("--")
    if (stringArray.tail.nonEmpty) {
      Some(stringArray.head)
    } else {
      None
    }
  }
}
