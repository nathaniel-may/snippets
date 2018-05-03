package com.scratchpad


object MaxPolyMod {

  def main(args: Array[String]): Unit = {
    val i1 = List(1,2)
    val i2 = List(3,4)
    val i3 = List(10, 1, 3)
    println("empty list inputs are ok. should be 4")
    println(getMax(List(List(), i1), 5))
    println("all empty lists produce 0")
    println(getMax(List(List(), List()), 5))
    println("this empty full empty set should produce 4")
    println(getMax(List(List(), i1, List()), 5))
    println("answer should be 3")
    println(getMax(List(i1, i2), 5))
    println("lists can be different lengths. answer should be 10")
    println(getMax(List(i1, i2, i3), 11))
  }

  /** Problem statement:
    * Given K lists of ints and an int M, for each list
    * pick one int from each list such that
    * (k_0^2 + k_1^2 ... k_i^2) mod M
    * is the largest possible number
    *
    * @param l must pick one int from each list
    * @param m the int to modulo the entire polynomial
    * @return the largest possible number given the
    *         choices from each list
    */
  def getMax(l: List[List[Int]], m: Int): Int = {
    val totals = cross(l).map(l => l.foldLeft(0)((res, i) => (res + i*i)%m))
    if (totals.isEmpty) 0 else totals.max
  }

  /** Computes the cartesian product for all lists in lists
    *
    * @param lists input lists of varying length to compute the product
    * @tparam X enforces all lists to contain the same type
    * @return The product. Each element in the product is a List[X]
    */
  def cross[X](lists: List[List[X]]): List[List[X]] = {
    lists.foldLeft(List[List[X]]())((prod, list) =>
      if (prod.isEmpty) list.map(l => List(l))
      else if (list.isEmpty) prod
      else prod.flatMap(r => list.map(l => l+:r)))
  }

}