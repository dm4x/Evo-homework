package lesson_4_collections

object ArrayShuffle {
  def shuffle(nums: Array[Int], n: Int): Array[Int] = {
    val numbers = nums.toList
    (numbers.take(n) zip numbers.drop(n) flatMap{case (x,y) => List(x,y)}).toArray
  }
}
