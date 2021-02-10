package lesson_4

import scala.annotation.tailrec

object BalancedStrings {
  def balancedStringSplit(s: String): Int = {
    calcDepth(s.toList, 0, 0)
  }

  @tailrec
  def calcDepth(list: List[Char], acc: Int, max: Int): Int = list match {
    case Nil => max
    case _ if list.head == 'R' => calcDepth(list.tail, acc + 1, if (acc == 0) max + 1 else max)
    case _ if list.head == 'L' => calcDepth(list.tail, acc - 1, if (acc == 0) max + 1 else max)
    case _ => calcDepth(list.tail, acc, max)
  }
}
