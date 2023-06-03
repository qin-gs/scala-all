package com.learn.basic

import scala.util.Random

object PatternMatching {

  def main(args: Array[String]): Unit = {
    val x: Int = Random.nextInt(10)
    println(matchTest(x))
    println(matchTest(7))

    val someSms = SMS("1234", "are you there?")
    val someVoceRecording = VoiceRecording("tom", "voicerecording.ort")
    println(showNotification(someSms))
    println(showNotification(someVoceRecording))

    testNotification()
  }

  def matchTest(x: Int): String = x match {
    case 0 => "zero"
    case 1 => "one"
    case 2 => "two"
    // 匹配其他所有情况
    case _ => "other"
  }

  abstract class Notification

  case class Email(sender: String, title: String, body: String) extends Notification

  case class SMS(caller: String, message: String) extends Notification

  case class VoiceRecording(contactName: String, link: String) extends Notification

  def showNotification(notification: Notification): String = {
    notification match {
      // 可以忽略 body
      case Email(sender, title, _) =>
        s"you got an email from $sender with title $title"
      case SMS(number, message) =>
        s"you got an sms from $number, message: $message"
      case VoiceRecording(name, link) =>
        s"you received a voice recording from $name, click link: $link"
    }
  }

  /**
   * 模式守卫
   */
  def showImportantNotification(notification: Notification, importantPeopleInfo: Seq[String]): String = {
    notification match {
      case Email(sender, _, _) if importantPeopleInfo.contains(sender) =>
        "you got an email from special someone"
      case SMS(number, _) if importantPeopleInfo.contains(number) =>
        "you got an sms from special someone"
      case other =>
        showNotification(other)
    }
  }

  def testNotification(): Unit = {
    val importantPeopleInfo = Seq("123-456", "a@gmail.com")
    val someSms = SMS("123-456", "how are you")
    val someVoiceRecording = VoiceRecording("tom", "voicerecording.org/id/123")
    val importantEmail = Email("q@gamil.com", "drinks tonight?", "I'm free after 5")
    val importantSms = SMS("123-456", "I'm here")

    println(showImportantNotification(someSms, importantPeopleInfo))
    println(showImportantNotification(someVoiceRecording, importantPeopleInfo))
    println(showImportantNotification(importantEmail, importantPeopleInfo))
    println(showImportantNotification(importantSms, importantPeopleInfo))
  }

  def matchType(): Unit = {
    abstract class Device
    case class Phone(model: String) extends Device {
      def screenOff = "Turning screen off"
    }
    case class Computer(model: String) extends Device {
      def screenSaverOn = "Turning screen saver on..."
    }

    /**
     * 仅匹配类型
     */
    def goIdle(device: Device) = device match {
      case p: Phone => p.screenOff
      case c: Computer => c.screenSaverOn
    }
  }

  /**
   * 密封类
   */
  def sealedMatch(): Unit = {
    // 意味着其所有子类都必须与之定义在相同文件中，从而保证所有子类型都是已知的
    sealed abstract class Furniture
    case class Couch() extends Furniture
    case class Chair() extends Furniture

    def findPlaceToSit(piece: Furniture): String = piece match {
      case a: Couch => "Lie on the couch"
      case b: Chair => "Sit on the chair"
    }
  }
}
