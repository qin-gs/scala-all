package socket

import java.io.ObjectInputStream
import java.net.ServerSocket

object Executor2 {

  def main(args: Array[String]): Unit = {
    val server = new ServerSocket(9000)
    println("9000 等待接收数据...")

    val client = server.accept()
    val is = client.getInputStream
    val ois = new ObjectInputStream(is)
    val task = ois.readObject().asInstanceOf[Task]

    val ans = task.compute()
    println("9000 计算结果: " + ans)

  }
}
