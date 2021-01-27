package lesson_1

import scala.annotation.tailrec

object BasicsHomeWork extends App {

  @tailrec
  def gcd(a: Int, b: Int): Int = (a, b) match {
    case (a, 0) => a
    case _ => gcd(b, a % b)
  }

  def lcm(a: Int, b: Int): Option[Int] = {
    if (a == 0 || b == 0) None
    else Option(Math.abs(a / gcd(a, b) * b))
  }

}
