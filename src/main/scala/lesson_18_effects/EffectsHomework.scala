package lesson_18_effects

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object EffectsHomework1 {
  final class IO[A](private val run: () => A) {
    def map[B](f: A => B): IO[B] = new IO(() => f(run()))
    def flatMap[B](f: A => IO[B]): IO[B] = f(run())
    def *>[B](another: IO[B]): IO[B] = another
    def as[B](newValue: => B): IO[B] = new IO(() => newValue)
    def void: IO[Unit] = new IO(() => ())
    def attempt: IO[Either[Throwable, A]] = run() match {
      case None => new IO(() => Left(Throwable))
      case value => new IO(() => Right(value))
    }
    def option: IO[Option[A]] = new IO(() => Option(run()))
    def handleErrorWith[AA >: A](f: Throwable => IO[AA]): IO[AA] = ???
    def redeem[B](recover: Throwable => B, map: A => B): IO[B] = ???
    def redeemWith[B](recover: Throwable => IO[B], bind: A => IO[B]): IO[B] = ???
    def unsafeRunSync(): A = run()
    def unsafeToFuture(): Future[A] = Future{run()}
  }

  object IO {
    def apply[A](body: => A): IO[A] = new IO(() => body)
    def suspend[A](thunk: => IO[A]): IO[A] = thunk
    def delay[A](body: => A): IO[A] = apply(body)
    def pure[A](a: A): IO[A] = IO(a)
    def fromEither[A](e: Either[Throwable, A]): IO[A] = e match {
      case Left(error)  => raiseError(error)
      case Right(value) => pure(value)
    }
    def fromOption[A](option: Option[A])(orElse: => Throwable): IO[A] = option match {
      case None        => raiseError(orElse)
      case Some(value) => pure(value)
    }
    def fromTry[A](t: Try[A]): IO[A] = t match {
      case Failure(cause) => raiseError(cause)
      case Success(value) => pure(value)
    }
    def none[A]: IO[Option[A]] = pure(None)
    def raiseError[A](e: Throwable): IO[A] = new IO(() => throw e)

    // I think this is a bullshit code, down of that comment. Advise is needed :)
    def raiseUnless(cond: Boolean)(e: => Throwable): IO[Unit] = cond match {
      case false => raiseUnless(cond)(e) // <- here and below recursive call but condition will never be changed
      case true  => new IO(() => ())
    }
    def raiseUnless1(cond: Boolean)(e: => Throwable): IO[Unit] = cond match {
      case false => raiseError(e) // another solution, but in that case we haven't "unless", we just have one iteration
      case true  => unit
    }
    def raiseWhen(cond: Boolean)(e: => Throwable): IO[Unit] = cond match {
      case false => new IO(() => ())
      case true  => raiseWhen(cond)(e)
    }
    def unlessA(cond: Boolean)(action: => IO[Unit]): IO[Unit] = cond match {
      case false => unlessA(cond)(action)
      case true  => action
    }
    def whenA(cond: Boolean)(action: => IO[Unit]): IO[Unit] = cond match {
      case false => action
      case true  => whenA(cond)(action)
    }
    val unit: IO[Unit] = new IO(() => ())
  }
}
