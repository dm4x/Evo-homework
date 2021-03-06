package lesson_4_collections

import lesson_4_collections.GreedyKids.kidsWithCandies
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatest.flatspec.AnyFlatSpec

class GreedyKidsTest extends AnyFlatSpec {
  "kidsWithCandies" should "find richest balance" in {
    kidsWithCandies( Array(2,3,5,1,3), 3 ) shouldEqual Array(true,true,true,false,true)
    kidsWithCandies( Array(4,2,1,1,2), 1) shouldEqual Array(true,false,false,false,false)
    kidsWithCandies( Array(12,1,12), 10) shouldEqual Array(true,false,true)
  }
}
