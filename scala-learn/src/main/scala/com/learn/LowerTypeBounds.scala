package com.learn

/**
 * 类型下界: B >: A 表示类型参数 B 或抽象类型 B 是类型 A 的超类型
 */
object LowerTypeBounds {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 协变
   */
  trait Node[+B] {
    def prepend[U >: B](elem: U): Node[U]
  }

  case class ListNode[+B](h: B, t: Node[B]) extends Node[B] {
    override def prepend[U >: B](elem: U): ListNode[U] = ListNode(elem, this)

    def head: B = h

    def tail: Node[B] = t
  }

  case class Nil[+B]() extends Node[B] {
    override def prepend[U >: B](elem: U): Node[U] = ListNode(elem, this)
  }
}
