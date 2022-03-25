package chapter_1

import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object MyIO {
  case class MyIO[A](unsafeRun:()=>A){
    def map[B](f:A=>B):MyIO[B]={
      MyIO(()=>f(unsafeRun()))
    }
    def flatMap[B](f:A=>MyIO[B]):MyIO[B]={
      MyIO(()=>f(unsafeRun()).unsafeRun())
    }
  }
  object MyIO{
    def putStr(s: =>String ):MyIO[Unit]={
      MyIO(()=>print(s))
    }
  }
  def main(args:Array[String]):Unit={
    val hello = MyIO.putStr("hello")
    val space = MyIO.putStr(" ")
    val world = MyIO.putStr("world")
    val endOfLine = MyIO.putStr("\n")
    (for{
      one <- hello
      two <- space
      three <- world
      four<- endOfLine
    }yield ()).unsafeRun()
  }
}


