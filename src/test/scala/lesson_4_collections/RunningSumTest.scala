package lesson_4_collections

import lesson_4_collections.RunningSum.runningSum
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class RunningSumTest extends AnyFlatSpec {

  "runningSum" should "calculate running sum of array" in {
    runningSum(Array(1,2,3,4)) shouldEqual Array(1,3,6,10)
    runningSum(Array(1,1,1,1,1)) shouldEqual Array(1,2,3,4,5)
    runningSum(Array(3,1,2,10,1)) shouldEqual Array(3,4,6,16,17)
  }

}
