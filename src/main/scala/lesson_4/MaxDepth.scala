package lesson_4

import scala.annotation.tailrec

object MaxDepth extends App {
  def maxDepth(s: String): Int = {
    calcDepth(s.replaceAll("\\s", "").toList, 0, 0)
  }

  @tailrec
  def calcDepth(list: List[Char], acc: Int, max: Int): Int = list match {
    case Nil => max
    case _ if list.head == '(' => calcDepth(list.tail, acc + 1, if (max < acc) acc else max)
    case _ if list.head == ')' => calcDepth(list.tail, acc - 1, if (max < acc) acc else max)
    case _ => calcDepth(list.tail, acc, max)
  }
}
