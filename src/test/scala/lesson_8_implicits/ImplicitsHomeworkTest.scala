package lesson_8_implicits

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import lesson_8_implicits.TypeclassTask._
import lesson_8_implicits.Task2._
import lesson_8_implicits.Task3.ParseSyntax
import lesson_8_implicits.Task2.User

class ImplicitsHomeworkTest  extends AnyFlatSpec with Matchers{

  "Task 1" should "" in {
    "abc".hash shouldBe "96354-some-hash-magic"
  }

  "Task 2" should "" in {
    User("1", "Oleg").show shouldBe "User with id=1 and name=Oleg"
    User("2", "Olga").show shouldBe "User with id=2 and name=Olga"
  }

  "Task 3" should "" in {
    "lalala".parse shouldBe Left("unknown format")
    "{id=1,name=Oleg}".parse shouldBe Left("unknown format")
    "{id=1, name=Oleg}".parse shouldBe Right(User(id="1", name="Oleg"))
  }
  
}
