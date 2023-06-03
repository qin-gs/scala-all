package com.learn.basic

/**
 * 型变: 协变，逆变，不变
 * class Foo[+A] // A covariant class 如果 A 是 B 的子类型，那么 List[A] 就是 List[B] 的子类型
 * class Bar[-A] // A contravariant class 如果 A 是 B 的子类型，那么 Writer[B] 是 Writer[A] 的子类型
 * class Baz[A]  // An invariant class
 */
object Variance {

  def main(args: Array[String]): Unit = {
    val cats: List[Cat] = List(Cat("mimi"), Cat("momo"))
    val dogs: List[Dog] = List(Dog("lala"), Dog("lolo"))

    printAnimalNames(cats)
    printAnimalNames(dogs)

    // val catContainer: Container[Cat] = new Container(Cat("mimi"))
    // val animalContainer: Container[Animal] = catContainer
    // animalContainer.setValue(Dog("lala"))
    // val cat: Cat = catContainer.getValue
  }

  def printAnimalNames(animals: List[Animal]): Unit = {
    animals.foreach {
      animal => println(animal.name)
    }
  }

  abstract class Animal {
    def name: String
  }

  case class Cat(name: String) extends Animal

  case class Dog(name: String) extends Animal

  abstract class Printer[-A] {
    def print(value: A): Unit
  }

  /**
   * 如果 Printer[Cat] 知道如何在控制台打印出任意 Cat，
   * 并且 Printer[Animal] 知道如何在控制台打印出任意 Animal，
   * 那么 Printer[Animal] 也应该知道如何打印出 Cat
   */
  class AnimalPrinter extends Printer[Animal] {
    override def print(animal: Animal): Unit = {
      println("the animal's name is: " + animal.name)
    }
  }

  class CatPrinter extends Printer[Cat] {
    override def print(cat: Cat): Unit = {
      println("the cat's name is: " + cat.name)
    }
  }

  class Container[A](value: A) {
    private var _value: A = value

    def getValue: A = _value

    def setValue(value: A): Unit = {
      _value = value
    }
  }

}
