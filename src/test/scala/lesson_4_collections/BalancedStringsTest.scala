package lesson_4_collections

import lesson_4_collections.BalancedStrings.balancedStringSplit
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class BalancedStringsTest extends AnyFlatSpec {
  "balancedStringSplit" should "calculate shuffle of array" in {
    balancedStringSplit("RLRRLLRLRL") shouldEqual 4
    balancedStringSplit("RLLLLRRRLR") shouldEqual 3
    balancedStringSplit("LLLLRRRR") shouldEqual 1
  }
}
