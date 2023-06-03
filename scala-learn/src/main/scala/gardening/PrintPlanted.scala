package gardening

import gardening.fruits._
object PrintPlanted {

  def main(args: Array[String]): Unit = {
    for (fruit <- planted) {
      showFruit(fruit)
    }
  }
}
