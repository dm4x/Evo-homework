package lesson_3_control_structures

import scala.io.Source

object ControlStructuresHomework {

  // Homework

  // Create a command line application that reads various "commands" from the
  // stdin, evaluates them, and writes output to stdout.

  // Commands are:

  //   divide 4 5
  // which should output "4 divided by 5 is 0.8"

  //   sum 5 5 6 8.5
  // which should output "the sum of 5 5 6 8.5 is 24.5"

  //   average 4 3 8.5 4
  // which should output "the average of 4 3 8.5 4 is 4.875"

  //   min 4 -3 -17
  // which should output "the minimum of 4 -3 -17 is -17"

  //   max 4 -3 -17
  // which should output "the maximum of 4 -3 -17 is 4"

  // In case of commands that cannot be parsed or calculations that cannot be performed,
  // output a single line starting with "Error: "

  sealed trait Command {
    def evaluate: Either[ErrorMessage, Double]

    def name: String
  }

  object Command {

    final case class Divide(dividend: Double, divisor: Double, name: String = "divide") extends Command {
      override def evaluate: Either[ErrorMessage, Double] = divisor match {
        case 0 => Left(DivisionByZero())
        case _ => Right(dividend / divisor)
      }
    }

    final case class Sum(numbers: List[Double], name: String = "sum") extends Command {
      override def evaluate: Either[ErrorMessage, Double] = Right(numbers.sum)
    }

    final case class Average(numbers: List[Double], name: String = "average") extends Command {
      override def evaluate: Either[ErrorMessage, Double] = numbers match {
        case List() => Left(DivisionByZero())
        case numbers => Right(numbers.sum / numbers.length)
      }
    }

    final case class Min(numbers: List[Double], name: String = "minimum") extends Command {
      override def evaluate: Either[ErrorMessage, Double] = Right(numbers.min)
    }

    final case class Max(numbers: List[Double], name: String = "maximum") extends Command {
      override def evaluate: Either[ErrorMessage, Double] = Right(numbers.max)
    }

  }

  sealed trait ErrorMessage {
    def value: String
  }
  final case class CastingError(value: String = "Error: cannot cast to Double") extends ErrorMessage
  final case class NullOperands(value: String = "Error: cannot apply command to null operands") extends ErrorMessage
  final case class UnknownCommand(value: String = "Error: Unknown command") extends ErrorMessage
  final case class DivisionByZero(value: String = "Error: division by zero") extends ErrorMessage

  // Adjust `Result` and `ChangeMe` as you wish - you can turn Result into a `case class` and remove the `ChangeMe` if
  // you think it is the best model for your solution, or just have other `case class`-es implement `Result`
  sealed trait Result {
    def formatted: String
    def prettyDouble(value: Double): String = if (value.isWhole) f"$value%.0f" else value.toString
  }

  final case class DivisionResult(value: Either[ErrorMessage, Double], dividend: Double, divisor: Double) extends Result {
    override def formatted: String = s"${prettyDouble(dividend)} divided by ${prettyDouble(divisor)} is ${value.getOrElse(Left)}"
  }

  final case class OtherResults(value: Either[ErrorMessage, Double], command: String, operands: List[Double]) extends Result {
    override def formatted: String = s"the $command of ${operands.map(prettyDouble).mkString(" ")} is ${value.getOrElse(Left)}"
  }

  def parseCommand(x: String): Either[ErrorMessage, Command] =
    x.strip.replaceAll("[\\s]{2,}"," ").toLowerCase.split(' ').toList match {
    case command :: rest => stringsToDoubles(rest) match {
      case Left(error) => Left(error)
      case Right(value) => commandChoose(command, value)
    }
  }

  def stringsToDoubles(strings: List[String]): Either[ErrorMessage, List[Double]] = strings.map(_.toDoubleOption) match {
    case list if list.contains(None) => Left(CastingError())
    case list => Right(list.map(_.get))
  }

  def commandChoose(command: String, operands: List[Double]): Either[ErrorMessage, Command] = (command, operands) match {
    case ("divide", operands) => Right(Command.Divide(operands.head, operands.tail.head))
    case ("sum", operands) => Right(Command.Sum(operands))
    case ("average", operands) => Right(Command.Average(operands))
    case ("max", operands) => Right(Command.Max(operands))
    case ("min", operands) => Right(Command.Min(operands))
    case _ => Left(UnknownCommand())
  }

  // should return an error (using `Left` channel) in case of division by zero and other
  // invalid operations
  def calculate(command: Command): Either[ErrorMessage, Result] = (command, command.evaluate) match {
    case (_, Left(error)) => Left(error)
    case (Command.Divide(dividend, divisor, _), result) => Right(DivisionResult(result, dividend, divisor))
    case (Command.Average(operands, _), result) => Right(OtherResults(result, command.name, operands))
    case (Command.Sum(operands, _), result) => Right(OtherResults(result, command.name, operands))
    case (Command.Max(operands, _), result) => Right(OtherResults(result, command.name, operands))
    case (Command.Min(operands, _), result) => Right(OtherResults(result, command.name, operands))
  }

  def renderResult(result: Result): String = {
    result.formatted
  }

  def process(userInput: String): String = {
    val result = for {
      command <- parseCommand(userInput)
      rawResult <- calculate(command)
    } yield renderResult(rawResult)

    result match {
      case Left(error) => error.value
      case Right(value) => value
    }
  }

  // This `main` method reads lines from stdin, passes each to `process` and outputs the return value to stdout
  def main(args: Array[String]): Unit = Source.stdin.getLines() map process foreach println
}