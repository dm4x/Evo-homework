package lesson_8_implicits

import lesson_8_implicits.Task2.User

object TypeclassTask extends App{

  trait HashCode[T] {
    def hash(operand: T): String
  }

  object HashCode {
    def apply[F](implicit operand: HashCode[F]): HashCode[F] = operand
  }

  implicit class HashCodeSyntax[A](x: A) {
    // TODO: Implement syntax so I can "abc".hash
    def hash(implicit h: HashCode[A]): String = h.hash(x)
  }

  // TODO: make an instance for String
  implicit val stringInstance: HashCode[String] = baseString => s"${baseString.hashCode}-some-hash-magic"

  // TODO: write "abc".hash to check everything
  "abc".hash
}

// make as many exercises as you can

object Task1 {

  final case class Money(amount: BigDecimal)

  // TODO: create Ordering instance for Money
  implicit val moneyOrdering: Ordering[Money] = Ordering.by(_.amount)
}

object Task2 {

  trait Show[T] { // fancy toString
    def show(entity: T): String
  }

  final case class User(id: String, name: String)

  // TODO: create Show instance for User
  implicit val showInstance: Show[User] = user => s"User with id=${user.id} and name=${user.name}"

  // TODO: create syntax for Show so i can do User("1", "Oleg").show
  implicit class ShowSyntax[A](x: A) {
    def show(implicit s: Show[A]): String = s.show(x)
  }

}

object Task3 {
  type Error = String

  trait Parse[T] { // invent any format you want or it can be csv string
    def parse(entity: String): Either[Error, T]
  }

  final case class User(id: String, name: String)

  // "{id=1, name=Oleg}"
  val userRegex = raw"\{id=([0-9]+), name=([a-zA-Z]+)\}".r
  implicit val parseInstance: Parse[User] = {
    case userRegex(id, name) => Right(User(id, name))
    case _ => Left("unknown format")
  }

  implicit class ParseSyntax(x: String) {
    def parse[A](implicit p: Parse[A]): Either[Error, A] = p.parse(x)
  }

}

object Task4 {

  trait Equals[T] {
    def equ(a: T, b: T): Boolean
  }

  implicit val equalsInt: Equals[Int] = (a, b) => a.equals(b)
  implicit val equalsString: Equals[String] = (a, b) => a.equals(b)

  implicit class EqualsOps[A](a: A) {
    def === (b: A)(implicit impl: Equals[A]): Boolean = impl.equ(a, b)
  }

  2 === 2
  2 === "2"
}


object AdvancedHomework {
  // TODO: create a typeclass for flatMap method
}
