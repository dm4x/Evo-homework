package lesson_1_basics

import lesson_1_basics.BasicsHomeWork.{gcd, lcm}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class BasicsHomeWorkTest extends AnyFlatSpec {

  "gcd" should "calculate zero-operands" in {
    gcd(0, 0) shouldEqual 0
    gcd(0, 1) shouldEqual 1
    gcd(3, 0) shouldEqual 3
  }

  "gcd" should "calculate right" in {
    gcd(32, 12) shouldEqual 4
    gcd(24, 18) shouldEqual 6
    gcd(78, 14) shouldEqual 2
    gcd(14, 78) shouldEqual 2
    gcd(27, 13) shouldEqual 1
  }

  "lcm" should "calculate right" in {
    lcm(24, 18) shouldEqual Some(72)
    lcm(12, 8) shouldEqual Some(24)
    lcm(13, 27) shouldEqual Some(351)
  }

  "lcm" should "calculate negative operands" in {
    lcm(-27, 13) shouldEqual Some(351)
    lcm(27, -13) shouldEqual Some(351)
  }

  "lcm" should "get None if any value is 0" in {
    lcm(0, 1) shouldEqual None
    lcm(1, 0) shouldEqual None
    lcm(0, 0) shouldEqual None
  }

}


