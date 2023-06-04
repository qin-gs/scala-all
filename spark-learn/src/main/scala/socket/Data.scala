package socket

/**
 * 保存数据的结构
 */
class Data {

  val data: List[Int] = List(1, 2, 3, 4, 5)

  val logic: Int => Int = _ * 2
}
