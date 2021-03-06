package lesson_4_collections

import scala.annotation.tailrec

object DataStructures extends App {

  def allEqual[T](list: List[T]): Boolean = {

    @tailrec
    def helper(list: List[T], elem: T = list.head): Boolean = list match {
      case Nil => true
      case list if elem != list.head => false
      case _ => helper(list.tail, elem)
    }

    helper(list)
  }

}
