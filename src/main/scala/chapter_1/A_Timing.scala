package chapter_1

import scala.concurrent.duration.FiniteDuration
object A_Timing extends App {
  case class MyIO[A](unsafeRun: () => A) {
    def map[B](f: A => B): MyIO[B] = {
      MyIO(() => f(unsafeRun()))
    }

    def flatMap[B](f: A => MyIO[B]): MyIO[B] = {
      MyIO(() => f(unsafeRun()).unsafeRun())
    }
  }

  object MyIO {
    def putStr(s: => String): MyIO[Unit] = {
      MyIO(() => println(s))
    }
  }
  val clock: MyIO[Long] = MyIO[Long](()=>System.currentTimeMillis())

  def time[A](action: MyIO[A]): MyIO[(FiniteDuration, A)] = {
    for{
      before <- clock
      op <- action
      after <- clock
    }yield (FiniteDuration.apply(after-before,"ms"),op)
  }

  val timedHello = A_Timing.time(MyIO.putStr("hello"))

  timedHello.unsafeRun() match {
    case (duration, _) => println(s"'hello' took $duration")
  }
}