package com.scratchpad

object Cards {

  //this representation of suits and card values makes it impossible to create an illegal card
  //and allows the card values iterable and comparable
  trait Suit
  case object Spades extends Suit
  case object Diamonds extends Suit
  case object Clubs extends Suit
  case object Hearts extends Suit

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

  //usable ordered lists of suits and values
  val suits = List(Spades, Diamonds, Clubs, Hearts)
  val values: Seq[CardValue.Value] = CardValue.values.toSeq.sorted

  case class Card(suit: Suit, value: CardValue.Value)

  //deck in the order they are printed irl
  val deck: List[Card] = (for { s <- List(Spades, Diamonds); v <- values.reverse } yield Card(s, v)) ++
                         (for { s <- List(Clubs, Hearts);    v <- values }         yield Card(s, v))

  //shuffle can be modified by changing rand and algorithm functions
  def shuffledDeck: List[Card] = {
    def rand(low: Int, high: Int) = (scala.math.random() * (high - low) + low).toInt
    //fisher yates is inherently imperative and is implemented as such
    def fisherYates(cards: List[Card]): List[Card] = {
      val mutableCards = cards.toArray
      for(i <- cards.indices){
        val r = rand(i, cards.length)
        val choice = mutableCards(r)
        mutableCards(r) = cards(i)
        mutableCards(i) = choice
      }
      //keeping all mutable state scoped to fisher yates implementation
      mutableCards.toList
    }
    fisherYates(deck)
  }

  def main(args: Array[String]) {
    println(shuffledDeck)
  }

}

