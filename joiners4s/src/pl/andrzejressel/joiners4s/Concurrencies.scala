package pl.andrzejressel.joiners4s

import pl.andrzejressel.joiners4s.concurrencies.EitherFirstSuccessful

/** Utility object for running
  * [[pl.andrzejressel.joiners4s.Joiners Custom Joiners]] and
  * [[java.util.concurrent.StructuredTaskScope.Joiner Java Joiners]] in a more
  * Scala-friendly way.
  *
  * This object has the same structure as the
  * [[pl.andrzejressel.joiners4s.Joiners Joiners]] object, but instead of
  * returning Joiners, it exposes an `apply` method of `2-22` arity + one that
  * takes a list of functions for each Joiner, so that they can be used in a
  * more Scala-friendly way.
  */
object Concurrencies {

  /** Provides a Scala-friendly way to use the
    * [[pl.andrzejressel.joiners4s.Joiners.Either Either Joiners]]
    */
  object Either {

    /** Provides a Scala-friendly way to use the
      * [[pl.andrzejressel.joiners4s.Joiners.Either.firstSuccessful Either.firstSuccessful Joiner]]
      */
    object FirstSuccessful extends EitherFirstSuccessful
  }
}
