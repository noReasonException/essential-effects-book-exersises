package chapter_1.scratch

object MyIO {
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
      MyIO(() => print(s))
    }
  }

  def main(args: Array[String]): Unit = {
    val hello = MyIO.putStr("hello")
    val space = MyIO.putStr(" ")
    val world = MyIO.putStr("world")
    val endOfLine = MyIO.putStr("\n")
    (for {
      one <- hello
      two <- space
      three <- world
      four <- endOfLine
    } yield ()).unsafeRun()
  }
}
