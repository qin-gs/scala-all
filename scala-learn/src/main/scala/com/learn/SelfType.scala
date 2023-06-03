package com.learn

/**
 * 自类型: 用于声明一个特质必须混入其他特质
 */
object SelfType {
  trait User {
    def username: String
  }

  /**
   * 声明了扩展该特质后必须混入 User 这个特征
   */
  trait Tweeter {
    // 定义了之后，username 可以在 tweet 中使用
    this: User =>
    def tweet(tweetText: String): Unit = println(s"$username: $tweetText")
  }

  /**
   * 该类必须混入 User
   */
  class VerifiedTweeter(val username_ : String) extends Tweeter with User {
    def username = s"real $username_"
  }

  def main(args: Array[String]): Unit = {
    val realBeyonce = new VerifiedTweeter("beyonce")
    realBeyonce.tweet("just spilled my glass of lemonade")
  }
}
