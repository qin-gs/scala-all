package com.learn.basic

import scala.collection.mutable.ArrayBuffer

/**
 * 接口 extends
 */
object Traits {

  def main(args: Array[String]): Unit = {

    val iter = new IntIterator(10)
    println(iter.next())
    println(iter.next())

    val animals = ArrayBuffer.empty[Pet]
    animals.append(new Dog("Bob"))
    animals.append(new Cat("mimi"))
    animals.foreach(pet => println(pet))
  }

  trait Iterator[A] {
    def hasNext: Boolean

    def next(): A
  }

  class IntIterator(to: Int) extends Iterator[Int] {

    private var current = 0

    override def hasNext: Boolean = current < to

    override def next(): Int = {
      if (hasNext) {
        val t = current
        current += 1
        t
      } else {
        0
      }
    }
  }

  trait Pet {
    val name: String
  }

  class Cat(val name: String) extends Pet

  class Dog(val name: String) extends Pet
}
