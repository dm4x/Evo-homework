package lesson_4_collections

import scala.annotation.tailrec

object MyScanLeft extends App {

  def scanLeft[T](zero: T)(list: List[T])(f: (T, T) => T): List[T] = {
    calc(list, List(), zero, f).reverse
  }

  @tailrec
  def calc[T](source: List[T], result: List[T], prev: T, f:(T, T) => T): List[T] = source match {
    case Nil => prev :: result
    case source => calc(source.tail, prev :: result, f(prev, source.head), f)
  }

}
