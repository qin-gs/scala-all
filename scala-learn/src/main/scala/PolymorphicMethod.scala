/**
 * 多态方法: Scala 中的方法可以按类型和值进行参数化
 */
object PolymorphicMethod {

  def listOfDuplicates[A](x: A, length: Int): List[A] = {
    if (length < 1) {
      Nil
    } else {
      // :: 表示将左侧的元素添加到右侧的列表中
      x :: listOfDuplicates(x, length - 1)
    }
  }

  def main(args: Array[String]): Unit = {
    println(listOfDuplicates[Int](3, 4))
    println(listOfDuplicates("hi", 5))
  }
}
