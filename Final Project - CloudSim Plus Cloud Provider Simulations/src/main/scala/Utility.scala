/*
 * Utility.scala
 *
 * Contains utility methods necessary during simulation execution, but not
 * necessarily important to the high level implementation
 *
 */

import java.util
import scala.collection.mutable.ListBuffer


object Utility {

  /*
   * Function to convert Scala list to Java list
   */
  def toJavaList[T](l: List[T]): util.List[T] = {
    val a = new util.ArrayList[T]
    l.map(a.add(_))
    a
  }


  /*
   * Function to convert Java list to Scala list
   */
  def toScalaList[T](l: util.List[T]): List[T] = {
    var a = ListBuffer[T]()
    for (r <- 0 until l.size) a += l.get(r)
    a.toList
  }


  /*
   * Function calculates the cost of a simulation based on
   * the utilized bandwidth, storage, and memory
   */
  def calculateCost(bwCost: Double, bw: Int, storageCost: Double, storage: Int, memoryCost: Double, memory: Int): Double = {

    val cost: Double = (bwCost * bw) + (storageCost * storage) + (memoryCost * memory)

    cost

  }

}
