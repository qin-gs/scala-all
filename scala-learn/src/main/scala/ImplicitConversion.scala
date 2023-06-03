import scala.language.implicitConversions

object ImplicitConversion {

  implicit def list2ordered[A](x: List[A])(implicit elem2ordered: A => Ordered[A]): Ordered[List[A]] =
    new Ordered[List[A]] {
      override def compare(that: List[A]): Int = 1
    }

  implicit def int2Integer(x: Int): Integer =
    java.lang.Integer.valueOf(x)
}
