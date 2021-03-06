package lesson_4_collections

import scala.annotation.tailrec

object MyCount extends App {
  def count(s: String): List[(Char, Int)] = {
    calcCount(s.toList.tail, 0, s.toList.head, List()).reverse
  }

  @tailrec
  def calcCount(list: List[Char], acc: Int, prev: Char, result: List[(Char, Int)]): List[(Char, Int)] = list match {
    case Nil => (prev, acc + 1) :: result
    case head :: _ if head != prev => calcCount(list.tail, 0, head, (prev, acc + 1) :: result)
    case head :: _ if head == prev => calcCount(list.tail, acc + 1, head, result)
  }

}
