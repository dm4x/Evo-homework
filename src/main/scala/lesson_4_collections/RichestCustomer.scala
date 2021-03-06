package lesson_4_collections

object RichestCustomer extends App {
  def maximumWealth(accounts: Array[Array[Int]]): Int = {
    accounts.map(arr => arr.sum).max
  }
}
