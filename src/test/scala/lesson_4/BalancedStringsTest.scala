package lesson_4

import lesson_4.BalancedStrings.balancedStringSplit
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class BalancedStringsTest extends AnyFlatSpec {
  "balancedStringSplit" should "calculate shuffle of array" in {
    balancedStringSplit("RLRRLLRLRL") shouldEqual 4
    balancedStringSplit("RLLLLRRRLR") shouldEqual 3
    balancedStringSplit("LLLLRRRR") shouldEqual 1
  }
}
