package lesson_4

import lesson_4.DataStructures.allEqual
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class DataStructuresTest extends AnyFlatSpec {

  "allEqual" should "work for lists which are all equal" in {
    allEqual(List("a", "a", "a", "a")) shouldBe true
  }

  "allEqual" should "work on 1 element list" in {
    allEqual(List("a")) shouldBe true
  }

  "allEqual" should "work for lists which are NOT all equal" in {
    allEqual(List("a", "a", "b", "a")) shouldBe false
  }
}
