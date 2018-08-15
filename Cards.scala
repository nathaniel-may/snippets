package com.scratchpad

object Cards {

  trait Suit
  case object Spades extends Suit
  case object Diamonds extends Suit
  case object Clubs extends Suit
  case object Hearts extends Suit

  val suits = List(Spades, Diamonds, Clubs, Hearts)
  val values: Seq[CardValue.Value] = CardValue.values.toSeq.sorted

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

  val deck: List[Card] = (for { s <- List(Spades, Diamonds); v <- values.reverse } yield Card(s, v)) ++
             (for { s <- List(Clubs, Hearts);    v <- values }         yield Card(s, v))

  def shuffledDeck: List[Card] = {
    def rand(low: Int, high: Int) = (scala.math.random() * (high - low) + low).toInt
    def fisherYates(cards: List[Card]): List[Card] = {
      val mutableCards = cards.toArray
      for(i <- cards.indices){
        val r = rand(i, cards.length)
        val choice = mutableCards(r)
        mutableCards.update(r, cards(i))
        mutableCards.update(i, choice)
      }
      mutableCards.toList
    }
    fisherYates(deck)
  }

  def main(args: Array[String]) {
    println(shuffledDeck)
  }

}

