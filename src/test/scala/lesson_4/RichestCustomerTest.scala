package lesson_4

import lesson_4.RichestCustomer.maximumWealth
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatest.flatspec.AnyFlatSpec

class RichestCustomerTest extends AnyFlatSpec {
  "maximumWealth" should "find richest balance" in {
    maximumWealth( Array( Array(1,2,3), Array(3,2,1) )) shouldEqual 6
    maximumWealth( Array( Array(1,5), Array(7,3), Array(3,5) )) shouldEqual 10
    maximumWealth( Array( Array(2,8,7), Array(7,1,3), Array(1,9,5))) shouldEqual 17
  }
}
