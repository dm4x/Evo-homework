package lesson_13_unit_testing

import lesson_13_unit_testing.UnitTestingHomework.Calculator
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.funsuite.AnyFunSuite

class UnitTestingFreeSpec extends AnyFreeSpec {

  "calculator" - {
    "enters the number correctly" in {
      val calculator = Calculator()
      assert(calculator.enter(0) == Calculator(0, 0, None))
      assert(calculator.enter(1) == Calculator(0, 1, None))
      assert(calculator.enter(7) == Calculator(0, 7, None))
      assert(calculator.enter(9) == Calculator(0, 9, None))
      assert(calculator.enter(3).enter(5).enter(6) == Calculator(0, 356, None))
      assert(calculator.enter(12) == Calculator(error = "digit out of range"))
      assert(calculator.enter(-1) == Calculator(error = "digit out of range"))
    }

    "cleaning correctly" in {
      val calculator = Calculator().enter(2).enter(5).plus.enter(3).enter(4).clean
      assert(calculator.screen == 0)
      assert(calculator.memory == 0)
      assert(calculator.operation.isEmpty)
      assert(calculator.error == "")
    }

    "adding correctly" in {
      assert(Calculator().enter(2).plus.enter(3).calculate.screen == 5)
      assert(Calculator().enter(3).enter(5).plus.enter(8).enter(6).calculate.screen == 121)
    }

    "does nothing" - {
      "when you just repeat pressing `=`" in {
        val calculator = Calculator()
        assert(calculator.calculate.calculate.calculate.calculate == calculator)
      }
    }
  }
}

class UnitTestingFunSpec extends AnyFunSuite {

  test("substracting: 2 - 3") {
    assert(Calculator().enter(2).minus.enter(3).calculate.screen == -1)
  }

  test("substracting: 354 - 86") {
    assert(Calculator().enter(3).enter(5).enter(4).minus.enter(8).enter(6).calculate.screen == 268)
  }

  test("multiplying: 354 * 86") {
    assert(Calculator().enter(3).enter(5).enter(4).multiply.enter(8).enter(6).calculate.screen == 30444)
  }

  test("multiplying: 0 * 86") {
    assert(Calculator().enter(0).multiply.enter(8).enter(6).calculate.screen == 0)
  }

  test("multiplying: 354 * 0") {
    assert(Calculator().enter(3).enter(5).enter(4).multiply.enter(0).calculate.screen == 0)
  }

  test("dividing: 6 / 3") {
    assert(Calculator().enter(6).divide.enter(3).calculate.screen == 2)
  }

  test("dividing: 6 / 0") {
    assert(Calculator().enter(6).divide.enter(0).calculate.error == "dividing by zero")
  }

  test("dividing: 0 / 6") {
    assert(Calculator().enter(0).divide.enter(6).calculate.screen == 0)
  }
}

class UnitTestingFlatSpec extends AnyFlatSpec{

  "chained operations" should "be calculated correct" in {
    Calculator().enter(3).plus.enter(8).calculate.minus.enter(1).calculate.multiply.enter(2).calculate.screen shouldBe 20
    Calculator().enter(0).minus.enter(8).calculate.multiply.enter(2).calculate.screen shouldBe -16
    Calculator().enter(0).divide.enter(8).calculate.plus.enter(1).calculate.screen shouldBe 1
  }
}
