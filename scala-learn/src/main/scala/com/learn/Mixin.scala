package com.learn

/**
 * 混入
 */
object Mixin {

  def main(args: Array[String]): Unit = {
    val d = new D
    println(d.message)
    println(d.loadMessage)
  }

  abstract class A {
    val message: String
  }

  class B extends A {
    val message = "I'm an instance of class B"
  }

  trait C extends A {
    def loadMessage = message.toUpperCase()
  }

  /**
   * with 混入
   */
  class D extends B with C

  abstract class AbsIterator {
    type T

    def hasNext(): Boolean

    def next(): T
  }

  class StringIterator(s: String) extends AbsIterator {
    override type T = Char
    private var i = 0

    /**
     * 无参数方法是不接受参数的函数，通过没有任何空括号来定义。
     * 无参数函数的调用应该不带括号。
     * 这样可以在不改变客户端代码的情况下改变def到val，这是统一访问原则的一部分。
     */
    override def hasNext(): Boolean = i < s.length

    override def next(): Char = {
      val ch = s charAt i
      i += 1
      ch
    }
  }

  trait RichIterator extends AbsIterator {
    def foreach(f: T => Unit): Unit = while (hasNext) f(next())
  }

  object StringIteratorTest extends App {
    class RichStringIter extends StringIterator("scala") with RichIterator

    val richStringIter = new RichStringIter
    richStringIter foreach println
  }
}
