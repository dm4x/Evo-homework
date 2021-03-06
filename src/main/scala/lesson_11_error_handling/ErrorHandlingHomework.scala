package lesson_11_error_handling

import cats.data.ValidatedNec
import cats.implicits._

object ErrorHandlingHomework {

  case class PaymentCard(name: String,
                         number: String,
                         expirationDate: String,
                         securityCode: String)

  /*
  * Номера карт начинаются с
    2-Мир
    3- American Express, JCB International, Diners Club
    4- VISA
    5- MasterCard, Maestro
    6- Maestro, China UnionPay, Discover
    7-УЭК
  * */


  sealed trait ValidationError

  object ValidationError {

    final case object NumberIsInvalid extends ValidationError {
      override def toString: String = "Card's number must be a numeric type"
    }

    final case object NumberLengthIsInvalid extends ValidationError {
      override def toString: String = "Card's number must consist of 7, 10, 13, 16, 18 or 19 digits"
    }

    final case object PaymentSystemIsInvalid extends ValidationError {
      override def toString: String = "First digit of card's number must be between 2 and 7"
    }

    final case object NameHasSpecialCharactersOrDigits extends ValidationError {
      override def toString: String = "Username cannot contain special characters or digits"
    }

    final case object ExpirationDateIsInvalid extends ValidationError {
      override def toString: String = "Expiration date must be like 11/11"
    }

    final case object SecurityCodeIsInvalid extends ValidationError {
      override def toString: String = "Security code must consist of digits only"
    }

    final case object SecurityCodeLengthIsInvalid extends ValidationError {
      override def toString: String = "Security code must have a 3 digits length"
    }

  }

  object PaymentCardValidator {

    import ValidationError._

    type AllErrorsOr[A] = ValidatedNec[ValidationError, A]

    def validate(
                  name: String,
                  number: String,
                  expirationDate: String,
                  securityCode: String,
                ): AllErrorsOr[PaymentCard] = {

      def validateNumber(number: String): AllErrorsOr[String] = {
        val numberNoSpaces = number.replaceAll(" ", "")

        def validateNumberContents: AllErrorsOr[String] =
          if (numberNoSpaces.matches("^[0-9]+$")) number.validNec
          else NumberIsInvalid.invalidNec

        def validateNumberLength: AllErrorsOr[String] =
          if (Seq(7, 10, 13, 16, 18, 19).contains(numberNoSpaces.length)) number.validNec
          else NumberLengthIsInvalid.invalidNec

        def validatePaymentSystem: AllErrorsOr[String] =
          if (numberNoSpaces.substring(0, 1).toIntOption.getOrElse(0) >= 2
            && numberNoSpaces.substring(0, 1).toIntOption.getOrElse(0) <= 7) number.validNec
          else PaymentSystemIsInvalid.invalidNec

        validateNumberContents *> validateNumberLength *> validatePaymentSystem
      }

      def validateName(name: String): AllErrorsOr[String] = {
        if (name.matches("^[a-zA-Z\\s]+$")) name.validNec
        else NameHasSpecialCharactersOrDigits.invalidNec
      }

      def validateExpirationDate(expirationDate: String): AllErrorsOr[String] = {
        if (expirationDate.matches("^[0-9]{2}\\/[0-9]{2}+$")) expirationDate.validNec
        else ExpirationDateIsInvalid.invalidNec
      }

      def validateSecurityCode(securityCode: String): AllErrorsOr[String] = {
        def validateCodeContents: AllErrorsOr[String] =
          if (securityCode.matches("^[0-9]+$")) securityCode.validNec
          else SecurityCodeIsInvalid.invalidNec

        def validateCodeLength: AllErrorsOr[String] =
          if (securityCode.length == 3) securityCode.validNec
          else SecurityCodeLengthIsInvalid.invalidNec

        validateCodeContents *> validateCodeLength
      }

      (validateName(name),
        validateNumber(number),
        validateExpirationDate(expirationDate),
        validateSecurityCode(securityCode)
      ).mapN(PaymentCard)
    }
  }

}
