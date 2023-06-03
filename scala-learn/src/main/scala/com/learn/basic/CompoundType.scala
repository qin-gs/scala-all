package com.learn.basic

/**
 * 复合类型
 */
object CompoundType {

  trait Cloneable extends java.lang.Cloneable {
    override def clone(): AnyRef = {
      super.clone().asInstanceOf[Cloneable]
    }
  }

  trait Resetable {
    def reset: Unit
  }

  /**
   * 复合类型 with
   */
  def cloneAndReset(obj: Cloneable with Resetable): Cloneable = {
    val cloned = obj.clone()
    obj.reset
    obj
  }
}
