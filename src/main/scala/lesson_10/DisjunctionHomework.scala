package lesson_10

class HKTHomework {

  sealed trait Disjunction[+A, +B]
  object Disjunction {
    case class DLeft[+A](value: A) extends Disjunction[A, Nothing]
    case class DRight[+B](value: B) extends Disjunction[Nothing, B]

    def getLeft[A](value: A): Disjunction[A, Nothing] = DLeft(value)
    def getRight[B](value: B): Disjunction[Nothing, B] = DRight(value)
  }

  trait Functor[F[_]] {
    def fmap[A, B](fa: F[A])(f: A => B): F[B]
  }

  object Functor {
    def apply[F[_]: Functor]: Functor[F] = implicitly[Functor[F]]
  }

  // 2. Syntax helper
  implicit class FunctorOps[F[_]: Functor, A](fa: F[A]) {
    def fmap[B](f: A => B): F[B] = Functor[F].fmap(fa)(f)
  }

  // Exercise 12. Implement Functor for `Disjunction`
  implicit def disjunctionFunctor[T]: Functor[Disjunction[T, *]] = new Functor[Disjunction[T, *]] {
    override def fmap[A, B](fa: Disjunction[T, A])(f: A => B): Disjunction[T, B] = fa match {
      case Disjunction.DLeft(value) => Disjunction.DLeft(value)
      case Disjunction.DRight(value) => Disjunction.DRight(f(value))
    }
  }
}


