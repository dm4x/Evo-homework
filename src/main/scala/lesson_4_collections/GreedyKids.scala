package lesson_4_collections

object GreedyKids extends App {
  def kidsWithCandies(candies: Array[Int], extraCandies: Int): Array[Boolean] = {
    candies.map{kid => (kid + extraCandies) >= candies.max}
  }
}
