package lesson_4

import lesson_4.MaxDepth.maxDepth
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatest.flatspec.AnyFlatSpec

class MaxDepthTest extends AnyFlatSpec {
  "maxDepth" should "calculate shuffle of array" in {
    maxDepth("(1+(2*3)+((8)/4))+1") shouldEqual 3
    maxDepth("(1)+((2))+(((3)))") shouldEqual 3
    maxDepth("1+(2*3)/(2-1)") shouldEqual 1
  }
}
