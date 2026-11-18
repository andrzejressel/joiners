package pl.andrzejressel.joiners4s

import java.util.concurrent.Callable
import java.util.concurrent.StructuredTaskScope
import java.util.concurrent.StructuredTaskScope.Subtask
import io.arxila.javatuples
import scala.util.NotGiven

private object Extensions {

  extension [T, R, R_X <: Throwable](scope: StructuredTaskScope[T, R, R_X]) {
    def fork[U <: T](f: => U): Subtask[U] = {
      scope.fork(
        new Callable[U] {
          override def call(): U = f
        }
      )
    }
  }

  extension [T](f: () => T) {
    def toCallable: Callable[T] = {
      () => f()
    }
  }
  
  extension [A](a: A | Null) {
    def fold[B](ifNull: => B)(ifNotNull: A => B): B = {
      if a == null then ifNull
      else ifNotNull(a.nn) // After SIP-83 it can be removed
    }
    def mapNull[B](f: => B): A | B = {
      fold(f)(identity)
    }
    def throwOnNull[B](
        f: => B
    )(using tEv: B <:< Throwable)(using NotGiven[B =:= Nothing]): A = {
      mapNull(throw tEv(f))
    }
  }
  
  extension [T1, T2](tuple: javatuples.Tuple2[T1, T2]) {
    def toScala: (T1, T2) = {
      (tuple.value0(), tuple.value1())
    }
  }

}
