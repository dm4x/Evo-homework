package lesson_5

// Homework. Define all algebraic data types, which would be needed to implement “Hold’em Hand Strength”
// task you completed to join the bootcamp. Use your best judgement about particular data types to include
// in the solution, you can model concepts like:
//
// 1. Suit
// 2. Rank
// 3. Card
// 4. Hand (Texas or Omaha)
// 5. Board
// 6. Poker Combination (High Card, Pair, etc.)
// 7. Test Case (Board & Hands to rank)
// 8. Test Result (Hands ranked in a particular order for a particular Board, accounting for splits)
//
// Make sure the defined model protects against invalid data. Use value classes and smart constructors as
// appropriate. Place the solution under `adt` package in your homework repository.

sealed trait Combination {
  val rank: Rank
  val multiplier: Long
}

final case class Empty(rank: Rank, multiplier: Long = 1) extends Combination
final case class OnePair(rank: Rank, multiplier: Long = 100) extends Combination
final case class TwoPair(rank: Rank, secondRank: Int, multiplier: Long = 1000) extends Combination
final case class ThreeOfAKind(rank: Rank, multiplier: Long = 10000) extends Combination
final case class Straight(rank: Rank, multiplier: Long = 100_000) extends Combination
final case class Flush(rank: Rank, multiplier: Long = 1_000_000) extends Combination
final case class FullHouse(rank: Rank, lowRank: Rank, multiplier: Long = 10_000_000) extends Combination
final case class FourOfAKind(rank: Rank, multiplier: Long = 100_000_000) extends Combination
final case class StraightFlush(rank: Rank, multiplier: Long = 1_000_000_000) extends Combination

final case class Suit(value: Char) extends AnyVal
object Suit {
  def create(value: Char): Option[Char] = value match {
    case 'h' => Some(value)
    case 'c' => Some(value)
    case 's' => Some(value)
    case 'd' => Some(value)
    case _ => None
  }
}

final case class Rank private (value: Int) extends AnyVal
object Rank {
  def create(value: Int): Option[Int] = value match {
    case value if value > 0 && value < 14 => Some(value)
    case _ => None
  }
}

final case class Card(suit: Suit, rank: Rank) {
  override def toString: String = s"$rank$suit"
}
final case class Hand(cards: List[Card], combination: Combination, strength: Option[Int])
final case class Board(cards: List[Card])
final case class TestCase(board: Board, hands: List[Hand])
final case class TestResult(hands: List[Hand])
