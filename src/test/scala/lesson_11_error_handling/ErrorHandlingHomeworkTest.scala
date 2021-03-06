package lesson_11_error_handling

import cats.implicits.{catsSyntaxValidatedId, catsSyntaxValidatedIdBinCompat0}
import lesson_11_error_handling.ErrorHandlingHomework.ValidationError._
import lesson_11_error_handling.ErrorHandlingHomework._
import org.scalatest.Assertion
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks


class ErrorHandlingHomeworkTest extends AnyFlatSpec with Matchers with ScalaCheckDrivenPropertyChecks {
  "PaymentCardValidator" should "handle valid and invalid PaymentCards" in {

    PaymentCardValidator.validate(
      name = "name surname",
      number = "2234 5678 9000 1111",
      expirationDate = "11/23",
      securityCode = "123"
    ) shouldBe PaymentCard(name = "name surname", number = "2234 5678 9000 1111", expirationDate = "11/23", securityCode = "123").validNec

    def checkInvalid(name: String,
                     number: String,
                     expirationDate: String,
                     securityCode: String,
                     errors: Set[ValidationError]): Assertion =
      PaymentCardValidator.validate(
        name = name,
        number = number,
        expirationDate = expirationDate,
        securityCode = securityCode
      ).leftMap(_.toChain.toList.toSet) shouldBe errors.invalid

    checkInvalid(
      name = "1231",
      number = "2234 5678 9000 1111",
      expirationDate = "11/23",
      securityCode = "123",
      errors = Set(NameHasSpecialCharactersOrDigits),
    )
    checkInvalid(
      name = "name surname",
      number = "2234 5678 9000 1111",
      expirationDate = "1123",
      securityCode = "123",
      errors = Set(ExpirationDateIsInvalid),
    )
    checkInvalid(
      name = "name surname",
      number = "2234 5678 9000 1111",
      expirationDate = "11/23",
      securityCode = "123w56",
      errors = Set(SecurityCodeLengthIsInvalid, SecurityCodeIsInvalid),
    )
    checkInvalid(
      name = "name surname",
      number = "9234 5678 9000 1111",
      expirationDate = "11/23",
      securityCode = "123",
      errors = Set(PaymentSystemIsInvalid),
    )
    checkInvalid(
      name = "name surname",
      number = "9234 5678",
      expirationDate = "11/23",
      securityCode = "123",
      errors = Set(PaymentSystemIsInvalid, NumberLengthIsInvalid),
    )
    checkInvalid(
      name = "name surname",
      number = "9234 df",
      expirationDate = "11/23",
      securityCode = "123",
      errors = Set(PaymentSystemIsInvalid, NumberLengthIsInvalid, NumberIsInvalid),
    )
    checkInvalid(
      name = "name8 surname",
      number = "9234 5678 9000 1111",
      expirationDate = "",
      securityCode = "..",
      errors = Set(
        PaymentSystemIsInvalid,
        NameHasSpecialCharactersOrDigits,
        SecurityCodeLengthIsInvalid,
        SecurityCodeIsInvalid,
        ExpirationDateIsInvalid),
    )

  }

}
