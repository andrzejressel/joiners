package pl.andrzejressel.strcon4s

import pl.andrzejressel.strcon4s.Extensions.*

import java.util.concurrent.StructuredTaskScope
import java.util.concurrent.StructuredTaskScope.Joiner
import scala.jdk.CollectionConverters.*
import scala.util.Using

object Concurrencies {

  def getAll[A, B](
      a: => A,
      b: => B
  ): (A, B) = {
    val results = getAll(Seq(() => a, () => b))
    (results(0).asInstanceOf[A], results(1).asInstanceOf[B])
  }

  def getAll[A](
      functions: Seq[() => A]
  ): Seq[A] = {

    Using(
      StructuredTaskScope.open(
        Joiner.allSuccessfulOrThrow[A, Throwable](identity)
      )
    ) { scope =>
      functions.foreach(f => scope.fork(f()))
      scope.join()
    }.get.asScala.toSeq

  }

}
