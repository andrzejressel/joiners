package pl.andrzejressel.joiners4s

import pl.andrzejressel.joiners4s.joiners.AllUntilScalaJoiner
import pl.andrzejressel.joiners4s.joiners.FirstSuccessfulEitherJoiner
import pl.andrzejressel.joiners4s.joiners.ScalaJoiner
import pl.andrzejressel.joiners4s.joiners.ScalaJoiner.ScalaSubtask

/** Utility object with factory methods of various custom
  * [[java.util.concurrent.StructuredTaskScope.Joiner Joiners]]
  */
object Joiners {

  /** Additional factories for JDK
    * [[java.util.concurrent.StructuredTaskScope.Joiner Joiners]].
    */
  object Jdk {

    /** Wrapper around the JDK
      * [[java.util.concurrent.StructuredTaskScope.Joiner#allUntil() allUntil]]
      * that uses [[Seq]] and
      * [[pl.andrzejressel.joiners4s.joiners.ScalaJoiner.ScalaSubtask ScalaSubtask]].
      * Check original
      * [[java.util.concurrent.StructuredTaskScope.Joiner#allUntil() allUntil]]
      * documentation for details.
      */
    def allUntil[T](
        predicate: ScalaSubtask[T] => Boolean
    ): ScalaJoiner[T, Seq[ScalaSubtask[T]], Exception] = {
      AllUntilScalaJoiner[T](predicate)
    }
  }

  /** Joiners for [[Either]] values.
    */
  object Either {

    /** Returns a [[java.util.concurrent.StructuredTaskScope.Joiner Joiner]]
      * that will return the first successful result of type `B` or, if all
      * tasks fail, a sequence of all failures of type `A`.
      *
      * @tparam A
      *   the type of the failure values
      * @tparam B
      *   the type of the success values
      * @return
      *   a [[java.util.concurrent.StructuredTaskScope.Joiner Joiner]] that will
      *   return the first successful result or a sequence of failures
      */
    def firstSuccessful[A, B]
        : ScalaJoiner[Either[A, B], Either[Seq[A], B], Exception] = {
      FirstSuccessfulEitherJoiner[A, B]()
    }
  }

}
