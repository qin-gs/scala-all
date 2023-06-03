/**
 * 单例对象: 延迟创建的，当它第一次被使用时创建
 * 使用 object 定义
 */
object SingletonObj {
  def info(message: String): Unit = {
    println(s"info: $message")
  }

  def main(args: Array[String]): Unit = {
    SingletonObj.info("hi")

    val circle = Circle(4)
    println(circle.area)

    val aEmail = Email.fromString("qqq@gmail.com")
    aEmail match {
      case Some(email) => println(
        s"""Registered an email
           |username: ${email.username}
           |domain: ${email.domainName}
           |"""
      )
      case None => println("error: could not parse email")
    }

  }

  /**
   * 一个单例对象和某个类共享一个名称，这个单例对象称为伴生对象，类被称为是这个单例对象的伴生类
   * 类和它的伴生对象可以互相访问其私有成员
   */
  case class Circle(radius: Int) {

    import Circle._

    def area: Double = calculateArea(radius)
  }

  /**
   * 使用伴生对象来定义那些在伴生类中不依赖于实例化对象而存在的成员变量或者方法
   * 类和它的伴生对象必须定义在同一个源文件里
   */
  object Circle {

    import scala.math._

    private def calculateArea(radius: Int): Double =
      Pi * pow(radius, 2)
  }

  class Email(val username: String, val domainName: String)

  /**
   * static 成员，静态转发
   */
  object Email {
    def fromString(emailString: String): Option[Email] = {
      emailString.split('@') match {
        case Array(a, b) => Some(new Email(a, b))
        case _ => None
      }
    }
  }
}
