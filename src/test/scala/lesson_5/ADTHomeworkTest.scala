package lesson_5

import org.scalacheck.Gen.choose
import org.scalatest.OptionValues.convertOptionToValuable
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatest.prop.TableDrivenPropertyChecks.whenever
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks.forAll

class ADTHomeworkTest extends AnyFlatSpec{

  "Suit" should "allow being created with h c s and d" in {
    Suit.create('h').value shouldBe 'h'
    Suit.create('c').value shouldBe 'c'
    Suit.create('s').value shouldBe 's'
    Suit.create('d').value shouldBe 'd'
  }

  "Rank" should "allow being created with levels between 1 and 13" in {
    forAll(choose(min = 1, max = 13)) { v: Int =>
      Rank.create(v).value shouldBe v
    }
  }

  "Rank" should "forbid being created with invalid ranks" in {
    forAll { v: Int =>
      whenever(v < 1 || v > 13) {
        Rank.create(v) shouldBe None
      }
    }
  }
}
