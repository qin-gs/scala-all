package acc

import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

object Wc {

  def main(args: Array[String]): Unit = {
    val sc = getSc()

    val myAcc = new MyAccumulator()
    sc.register(myAcc, "myAccumulator")

    val rdd = sc.makeRDD(List("hello", "world", "hello", "spark"))

    rdd.foreach(
      word => {
        myAcc.add(word)
      })

    println(myAcc.value)

    sc.stop()
  }

  class MyAccumulator extends AccumulatorV2[String, mutable.Map[String, Long]] {

    private val wcMap = mutable.Map[String, Long]()

    override def isZero: Boolean = {
      wcMap.isEmpty
    }

    override def copy(): AccumulatorV2[String, mutable.Map[String, Long]] = {
      new MyAccumulator()
    }

    override def reset(): Unit = {
      wcMap.clear()
    }

    override def add(word: String): Unit = {
      val newCount = wcMap.getOrElse(word, 0L) + 1
      wcMap.update(word, newCount)
    }

    override def merge(other: AccumulatorV2[String, mutable.Map[String, Long]]): Unit = {
      val that = wcMap
      other.value.foreach {
        case (word, count) => {
          val newCount = that.getOrElse(word, 0L) + count
          that.update(word, newCount)
        }
      }
    }

    override def value: mutable.Map[String, Long] = {
      wcMap
    }
  }
}
