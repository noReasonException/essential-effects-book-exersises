package chapter_0.scratch

import cats.implicits._

object Scratchpad {
  def main(args:Array[String]):Unit={
    //.as helper
    val opt = Option(12).map(_=>10)
    val opt2= Option(12).as(10)
    println(opt+"-"+opt2)
    //.void helper
    val opt3 = Option(12).map(_=>())
    val opt4= Option(12).void
    println(opt3+"-"+opt4)
    //.composing with MapN
    val opt5 = Option(12)
    val opt6= Option(12)
    println((opt5,opt6).mapN((a,b)=>a+b))
    //.composing with MapN, ignore a
    val opt7 = Option(12)
    val opt8= Option(12)
    println(opt7*>opt8)

  }
}
