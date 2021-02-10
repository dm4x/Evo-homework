package lesson_4

object GreedyKids extends App {
  def kidsWithCandies(candies: Array[Int], extraCandies: Int): Array[Boolean] = {
    candies.map{kid => (kid + extraCandies) >= candies.max}
  }
}
