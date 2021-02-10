package lesson_4

import scala.annotation.tailrec

object RunningSum extends App {
  def runningSum(nums: Array[Int]): Array[Int] = {
    val numbers: List[Int] = nums.toList
    calc(numbers.tail, List(), numbers.head).reverse.toArray
  }

  @tailrec
  def calc(source: List[Int], result: List[Int], prev: Int): List[Int] = source match {
    case Nil => prev :: result
    case source => calc(source.tail, prev :: result, prev + source.head)
  }
}
