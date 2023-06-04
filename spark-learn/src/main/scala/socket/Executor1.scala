package socket

import java.io.ObjectInputStream
import java.net.ServerSocket

object Executor1 {

  def main(args: Array[String]): Unit = {
    val server = new ServerSocket(8000)
    println("8000 等待接收数据...")

    val client = server.accept()
    val is = client.getInputStream
    val ois = new ObjectInputStream(is)
    val task = ois.readObject().asInstanceOf[Task]

    val ans = task.compute()
    println("8000 计算结果: " + ans)

  }
}
