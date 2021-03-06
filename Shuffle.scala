package com.scratchpad

object Shuffle {

  def main(args: Array[String]): Unit = {
    //examples
    println(List(1,2,3,4,5,6,7,8).fisherYatesShuffle)
    println(List("My","name","is","Nate.").fisherYatesShuffle)
  }

  implicit class mapWithSwap[K, V](m: Map[K, V]){
    def swap(k1: K, k2: K): Option[Map[K, V]] =
      for {
        v1 <- m.get(k1)
        v2 <- m.get(k2)
      } yield m.updated(k1, v2).updated(k2, v1)
  }

  implicit class listWithShuffle[A](l: List[A]){
    def fisherYatesShuffle: List[A] = {
      //TODO use pure generator
      def rand(low: Int, high: Int) = scala.util.Random.nextInt(high - low) + low

      val map = l.zipWithIndex.map{_.swap}.toMap

      (0 until map.size)
        .foldLeft(map){ (m, i) =>
          m.swap(i, rand(i, m.size))
          .getOrElse(throw new Exception("shuffle implementation error")) }
        .valuesIterator.toList
    }
  }

}


