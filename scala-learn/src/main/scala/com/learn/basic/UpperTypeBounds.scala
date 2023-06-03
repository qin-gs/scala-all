package com.learn.basic

/**
 * 类型上界: T <: A 这样声明的类型上界表示类型变量T应该是类型A的子类
 */
object UpperTypeBounds {
  def main(args: Array[String]): Unit = {
    val dogContainer = new PetContainer[Dog](new Dog)
    val catContainer = new PetContainer[Cat](new Cat)
    // Lion 不是 Pet 的子类
    // val lionContainer = new PetContainer[Lion](new Lion)
  }

  abstract class Animal {
    def name: String
  }

  abstract class Pet extends Animal

  class Cat extends Pet {
    override def name: String = "Cat"
  }

  class Dog extends Pet {
    override def name: String = "Dog"
  }

  class Lion extends Animal {
    override def name: String = "Lion"
  }

  class PetContainer[P <: Pet](p: P) {
    def pet: P = p
  }
}
