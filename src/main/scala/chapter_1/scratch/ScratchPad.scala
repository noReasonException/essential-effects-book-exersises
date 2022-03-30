package chapter_1.scratch

import scala.concurrent.{ExecutionContext, Future}
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Effects checklist
 * 1) Is clear what kind of effect this type will have?
 * 2) Is clear what value this type will produce?
 * 3) If external effects are required, is the description separate from the execution?(i.e referentially transparent?)
 */
object ScratchPad {
  //Is Option an Effect?
  //Is clear what kind of effect this type will have? : Optionality
  //Is clear what value this type will produce? : Option[A] is either Some(A) or None (hence A or not A)
  //are external effects required? -> no, Option wraps over a constant value, does not produce results.
  //(although you can technically violate it if you are such an *******
  //hence Option[A] is indeed, an Effect (does not produce an effect tho)

  //is Future an effect?
  //Is clear what kind of effect this type will have? : Future value
  //Is clear what value this type will produce? : A if successful
  //external effects may be present(you can do whatever you want in a future). However when effects are present, Future does not separate
  //description from execution, the execution starts immediately, and only run once , and caches the result. -> Therefore is not ref transparent

  //The fact that starts immediately and caches the result, breaks the third axiom of an Effect type
  def main(args:Array[String]):Unit= {
    val future = Future {
      println("1 hello world")
    }
    future *> future *> future //prints hello world once

    Future {
      println("2 hello world")
    } *> Future {
      println("2 hello world")
    } *> Future {
      println("2 hello world")
    }//Prints hello world 3 times!

  }


}
