package lesson_3_control_structures

import lesson_3_control_structures.ControlStructuresHomework.process
import org.scalatest.matchers.should.Matchers._
import org.scalatest.flatspec.AnyFlatSpec

class ControlStructuresHomeworkTest extends AnyFlatSpec {

  it should "calculate right" in {
    process("") shouldEqual "Error: Unknown command"
    process("test") shouldEqual "Error: Unknown command"
    process("345364758") shouldEqual "Error: Unknown command"
    process("divide 4 0") shouldEqual "Error: division by zero"
    process("divide 4 sa") shouldEqual "Error: cannot cast to Double"
    process("average     4    3      8.5") shouldEqual "the average of 4 3 8.5 is 5.166666666666667"
    process("average 4 3 8.5") shouldEqual "the average of 4 3 8.5 is 5.166666666666667"
    process("average 4 3 8.5 4") shouldEqual "the average of 4 3 8.5 4 is 4.875"
    process("min 4 -3 -17") shouldEqual "the minimum of 4 -3 -17 is -17.0"
    process("max 4 -3 -17") shouldEqual "the maximum of 4 -3 -17 is 4.0"
    process("divide 4 5") shouldEqual "4 divided by 5 is 0.8"
    process("sum 5 5 6 8.5") shouldEqual "the sum of 5 5 6 8.5 is 24.5"
  }

}
