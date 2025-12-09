package compact.fp.io

import scala.io.StdIn
import scala.collection.immutable.LazyList

object Input {
  def readLines(): LazyList[String] =
    LazyList.continually(StdIn.readLine()).takeWhile(_ != null)
}
