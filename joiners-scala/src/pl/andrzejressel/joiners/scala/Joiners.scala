package pl.andrzejressel.joiners.scala

import pl.andrzejressel.joiners.scala.joiners.FirstSuccessfulEitherJoiner

import java.util.concurrent.StructuredTaskScope.Joiner

/** Utility object with factory methods of various [[java.util.concurrent.StructuredTaskScope.Joiner Joiner]]s
  */
object Joiners {

  /** Joiners for [[Either]] values.
    */
  object Either {

    /** Returns a [[java.util.concurrent.StructuredTaskScope.Joiner Joiner]] that will return the first successful result of
      * type `B` or, if all tasks fail, a sequence of all failures of type `A`.
      *
      * @tparam A
      *   the type of the failure values
      * @tparam B
      *   the type of the success values
      * @return
      *   a [[java.util.concurrent.StructuredTaskScope.Joiner Joiner]] that will return the first successful result or a
      *   sequence of failures
      */
    def firstSuccessful[A, B]
        : Joiner[Either[A, B], Either[Seq[A], B], Exception] = {
      FirstSuccessfulEitherJoiner[A, B]()
    }
  }

}
