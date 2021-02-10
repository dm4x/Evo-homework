package lesson_4

import lesson_4.MyCount.count
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class MyCountTest extends AnyFlatSpec {
  "count" should "pass" in {
    count("aaaabbbcca") shouldEqual List(('a', 4), ('b', 3), ('c', 2), ('a', 1))
    count("abbbbbccc") shouldEqual List(('a', 1), ('b', 5), ('c', 3))
  }

}
