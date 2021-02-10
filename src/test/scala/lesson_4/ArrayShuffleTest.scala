package lesson_4

import lesson_4.ArrayShuffle.shuffle
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class ArrayShuffleTest extends AnyFlatSpec {
  "shuffle" should "calculate shuffle of array" in {
    shuffle(Array(2,5,1,3,4,7), 3) shouldEqual Array(2,3,5,4,1,7)
    shuffle(Array(1,2,3,4,4,3,2,1), 4) shouldEqual Array(1,4,2,3,3,2,4,1)
    shuffle(Array(1,1,2,2), 2) shouldEqual Array(1,2,1,2)
  }
}
