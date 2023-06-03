package com.learn.basic

/**
 * 抽象类型
 */
object AbsType {
  def main(args: Array[String]): Unit = {

    val buf = newIntSeqBuf(4, 7)
    println(buf.length)
    println(buf.element)
  }

  trait Buffer {
    // 定义一个抽象类型
    type T
    val element: T
  }

  abstract class SeqBuffer extends Buffer {
    type U
    // 定义 T 的上界(T 只能是 Seq[U] 的子类)
    type T <: Seq[U]

    def length: Int = element.length
  }

  abstract class IntSeqBuffer extends SeqBuffer {
    type U = Int
  }

  def newIntSeqBuf(elem1: Int, elem2: Int): IntSeqBuffer =
    new IntSeqBuffer {
      override type T = List[U]
      override val element: List[Int] = List(elem1, elem2)
    }

  {
    /**
     * 把抽象类型成员转成类的类型参数或者反过来，也是可行的
     */
    abstract class Buffer[+T] {
      val element: T
    }
    abstract class SeqBuffer[U, +T <: Seq[U]] extends Buffer[T] {
      def length: Int = element.length
    }

    def newIntSeqBuf(e1: Int, e2: Int): SeqBuffer[Int, Seq[Int]] =
      new SeqBuffer[Int, List[Int]] {
        override val element: List[Int] = List(e1, e2)
      }
  }

}
