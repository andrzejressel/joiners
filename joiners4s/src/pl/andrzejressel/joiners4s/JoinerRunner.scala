package pl.andrzejressel.joiners4s

import pl.andrzejressel.joiners.JoinerRunner.joinCallables
import pl.andrzejressel.joiners4s.concurrencies.AllSuccessfulOrThrowJoinerRunner
import pl.andrzejressel.joiners4s.concurrencies.EitherFirstSuccessfulJoinerRunner

import java.util.concurrent.Callable
import java.util.concurrent.StructuredTaskScope
import scala.jdk.CollectionConverters.*

/** Utility object for running
  * [[pl.andrzejressel.joiners4s.Joiners Custom Joiners]] and
  * [[java.util.concurrent.StructuredTaskScope.Joiner Java Joiners]] in a more
  * Scala-friendly way.
  *
  * This object has the same structure as the
  * [[pl.andrzejressel.joiners4s.Joiners Joiners]] object, but instead of
  * returning Joiners, it exposes an `apply` method of `2-10` arity + one that
  * takes a list of functions for each Joiner, so that they can be used in a
  * more Scala-friendly way.
  */
object JoinerRunner {

  object JDK {
    object AllSuccessfulOrThrow extends AllSuccessfulOrThrowJoinerRunner
  }

  /** Provides a Scala-friendly way to use the
    * [[pl.andrzejressel.joiners4s.Joiners.Either Either Joiners]]
    */
  object Either {

    /** Provides a Scala-friendly way to use the
      * [[pl.andrzejressel.joiners4s.Joiners.Either.firstSuccessful Either.firstSuccessful Joiner]]
      */
    object FirstSuccessful extends EitherFirstSuccessfulJoinerRunner
  }

  def joinFunctions[T, R, R_X <: Throwable](
      joiner: StructuredTaskScope.Joiner[T, R, R_X],
      functions: List[() => T]
  ): R = {
    val callables = functions.map(f =>
      new Callable[T] {
        override def call(): T = f()
      }
    )
    joinCallables(joiner, callables.asJava)
  }

}
