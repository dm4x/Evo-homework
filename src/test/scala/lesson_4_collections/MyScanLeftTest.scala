package lesson_4_collections

import lesson_4_collections.MyScanLeft.scanLeft
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class MyScanLeftTest extends AnyFlatSpec {


  "scanLeft" should "work correctly on numbers" in {
    val numbers = (1 to 100).toList
    scanLeft(0)(numbers)(_ + _) shouldEqual numbers.scanLeft(0)(_ + _)
  }

  "scanLeft" should "work correctly on letters" in {
    val letters = ('a' to 'z').toList.map(_.toString)
    scanLeft("")(letters)(_ + _) shouldEqual letters.scanLeft("")(_ + _)
  }
}
