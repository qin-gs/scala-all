package com.learn.gardening

import scala.collection.immutable

/*
包对象: 包对象中的任何定义都被认为是包自身的成员
 */
package object fruits {

  val planted: immutable.Seq[Fruit] = List(Apple, Plum, Banana)

  def showFruit(fruit: Fruit): Unit = {
    println(s"${fruit.name}s are ${fruit.color}")
  }
}
