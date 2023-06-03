package com.learn.basic

object DefaultParamValues {

  def main(args: Array[String]): Unit = {
    // 可以忽略有默认值的参数
    log("404 not found", "error")
    log("finish start")

    // 命名参数，可以重新排列顺序
    // 如果只有某些参数被重命名，(未命名，命名)
    printName(last = "gs", first = "q")
  }

  def log(message: String, level: String = "info") = println(s"$level: $message")

  def printName(first: String, last: String): Unit = {
    println(first + " " + last)
  }
}
