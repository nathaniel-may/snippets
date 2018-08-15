package com.scratchpad

object Cards {

  trait Suit
  case object Spades extends Suit
  case object Diamonds extends Suit
  case object Clubs extends Suit
  case object Hearts extends Suit

  val suits = List(Spades, Diamonds, Clubs, Hearts)
  val values = CardValue.values.toSeq.sorted

  // Example of adding attributes to an enumeration by extending the Enumeration.Val class
  object CardValue extends Enumeration {
    protected case class Val(value: Int) extends super.Val {}
    implicit def valueToCardVal(x: Value): Val = x.asInstanceOf[Val]

    val King  = Val(13)
    val Queen = Val(12)
    val Jack  = Val(11)
    val Ten   = Val(10)
    val Nine  = Val(9)
    val Eight = Val(8)
    val Seven = Val(7)
    val Six   = Val(6)
    val Five  = Val(5)
    val Four  = Val(4)
    val Three = Val(3)
    val Two   = Val(2)
    val Ace   = Val(1)
  }

  case class Card(suit: Suit, value: CardValue.Value)

  val deck = (for { s <- List(Spades, Diamonds); v <- values.reverse } yield Card(s, v)) ++
             (for { s <- List(Clubs, Hearts);    v <- values }         yield Card(s, v))

  def shuffledDeck = {
    def rand(low: Int, high: Int) = (scala.math.random() * (high - low) + low).toInt
    def fisherYates(cards: Array[Card], index: Int=0): List[Card] = {
      if(index == cards.length) cards.toList
      else {
        val r = rand(index, cards.length)
        val choice = cards.lift(r).get
        fisherYates(cards.updated(r, cards.lift(index).get).updated(index, choice), index + 1)
      }
    }
    fisherYates(deck.toArray)
  }

  def main(args: Array[String]) {
    println(shuffledDeck)
  }

}

