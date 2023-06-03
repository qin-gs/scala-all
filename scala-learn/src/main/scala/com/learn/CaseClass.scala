package com.learn

/**
 * 样例类
 */
object CaseClass {
  def main(args: Array[String]): Unit = {
    // 实例化样例类，不使用 new (有一个默认的 apply 方法)
    val frankenstein = Book("978-2787520")

    // 所有的参数都是 public，不可重新赋值
    val message1 = Message("a@gmail.com", "b@gmail.com", "hi")
    println(message1.sender)

    // 按值进行比较
    val message2 = Message("a@gmail.com", "b@gmail.com", "hello")
    val isSame = message1 == message2
    println(isSame)

    // 浅拷贝，可以指定构造参数做一些改变
    val message3 = message1.copy(sender = message1.recipient, recipient = "new@gmail.com")
    println(message3)
  }

  case class Book(isbn: String)

  case class Message(sender: String, recipient: String, body: String)
}
