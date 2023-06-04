package socket

import java.io.ObjectOutputStream
import java.net.Socket

object Driver {

  def main(args: Array[String]): Unit = {
    val client1 = new Socket("localhost", 8000)
    val os1 = client1.getOutputStream
    val oos1 = new ObjectOutputStream(os1)

    val dataStructure = new Data
    val task1 = new Task
    task1.logic = dataStructure.logic
    task1.data = dataStructure.data.take(2)

    oos1.writeObject(task1)
    oos1.flush()
    oos1.close()

    println("8000 发送成功")


    val client2 = new Socket("localhost", 9000)
    val os2 = client2.getOutputStream
    val oos2 = new ObjectOutputStream(os2)

    val task2 = new Task
    task2.logic = dataStructure.logic
    task2.data = dataStructure.data.takeRight(3)

    oos2.writeObject(task2)
    oos2.flush()
    oos2.close()

    println("9000 发送成功")

  }
}
