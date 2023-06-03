package com.learn.gardening

import com.learn.gardening.fruits._
object PrintPlanted {

  def main(args: Array[String]): Unit = {
    for (fruit <- planted) {
      showFruit(fruit)
    }
  }
}
