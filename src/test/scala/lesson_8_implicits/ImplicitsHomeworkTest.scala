package lesson_8_implicits

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import lesson_8_implicits.TypeclassTask._
import lesson_8_implicits.Task2._
import lesson_8_implicits.Task3.ParseSyntax

class ImplicitsHomeworkTest  extends AnyFlatSpec with Matchers{

  "Task 1" should "give correct hash" in {
    "abc".hash shouldBe "96354-some-hash-magic"
  }

  "Task 2" should "be unapplied correctly to String" in {
    Task2.User("1", "Oleg").show shouldBe "User with id=1 and name=Oleg"
    Task2.User("2", "Olga").show shouldBe "User with id=2 and name=Olga"
  }

  "Task 3" should "be parsed to Either" in {
    "lalala".parse shouldBe Left("unknown format")
    "{id=1,name=Oleg}".parse shouldBe Left("unknown format")
    "{id=1, name=Oleg}".parse shouldBe Right(Task3.User(id="1", name="Oleg"))
  }
  
}
