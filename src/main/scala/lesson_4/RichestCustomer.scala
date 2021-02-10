package lesson_4

object RichestCustomer extends App {
  def maximumWealth(accounts: Array[Array[Int]]): Int = {
    accounts.map(arr => arr.sum).max
  }
}
