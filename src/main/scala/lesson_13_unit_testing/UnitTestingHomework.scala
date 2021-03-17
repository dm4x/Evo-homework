package lesson_13_unit_testing

object UnitTestingHomework extends App {

  import Calculator._

  /** Simple calculator with buttons.
   *
   * @param memory whatever is stored in the memory.
   * @param screen whatever you see on the screen.
   */
  case class Calculator(memory: Int = 0, screen: Int = 0, operation: Option[Operation] = None, error: String = "") {

    def enter(digit: Int): Calculator =
      if (digit >= 0 && digit <= 9) this.copy(screen = screen * 10 + digit)
      else this.copy(error = "digit out of range")

    def plus: Calculator = this.copy(memory = screen, screen = 0, operation = Some(Operation.Plus))
    def minus: Calculator = this.copy(memory = screen, screen = 0, operation = Some(Operation.Minus))
    def multiply: Calculator = this.copy(memory = screen, screen = 0, operation = Some(Operation.Multiply))
    def divide: Calculator = this.copy(memory = screen, screen = 0, operation = Some(Operation.Divide))
    def clean: Calculator = Calculator(memory = 0, screen = 0, operation = None, error = "")

    def calculate: Calculator = operation.fold(this) {
      case Operation.Plus => Calculator(screen = memory + screen)
      case Operation.Minus => Calculator(screen = memory - screen)
      case Operation.Multiply => Calculator(screen = memory * screen)
      case Operation.Divide if screen == 0 => Calculator(memory = 0, screen = 0, error="dividing by zero")
      case Operation.Divide => Calculator(screen = memory / screen)
    }
  }

  object Calculator {
    sealed trait Operation
    object Operation {
      object Plus extends Operation
      object Minus extends Operation
      object Multiply extends Operation
      object Divide extends Operation
      object Clean extends Operation
    }
  }
}