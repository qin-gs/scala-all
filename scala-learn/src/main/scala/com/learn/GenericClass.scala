package com.learn

/**
 * https://blog.csdn.net/weixin_58330858/article/details/121698619
 */
object GenericClass {

  def main(args: Array[String]): Unit = {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    println(stack.peek)
    println(stack.pop)
    println(stack.pop)
  }

  class Stack[A] {
    private var elements: List[A] = Nil

    // 将 elements 放到了一个头部为 x 后续为 elements 的新列表中
    def push(x: A): Unit =
      elements = x :: elements

    def peek: A = elements.head

    def pop: A = {
      val currentTop = peek
      elements = elements.tail
      currentTop
    }
  }
}
