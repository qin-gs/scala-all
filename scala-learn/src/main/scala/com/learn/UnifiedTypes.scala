package com.learn

object UnifiedTypes {

  def main(args: Array[String]): Unit = {

    val list: List[Any] = List(
      "a string",
      231,
      'c',
      true,
      () => " a anonymous function"
    )
    list.foreach(e => println(e))

    // byte, short, int, long, float, double
    // char

    // Nothing
    // 所有类型的子类型，给出非正常终止的信号，如抛出异常、程序退出或者一个无限循环
    // Null
    // 所有引用类型(AnyRef)的子类型
  }
}
