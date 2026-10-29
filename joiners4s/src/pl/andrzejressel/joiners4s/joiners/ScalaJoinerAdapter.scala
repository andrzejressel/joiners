package pl.andrzejressel.joiners4s.joiners

import pl.andrzejressel.joiners4s.joiners.ScalaJoiner.FailedSubtask
import pl.andrzejressel.joiners4s.joiners.ScalaJoiner.JoinerResult
import pl.andrzejressel.joiners4s.joiners.ScalaJoiner.SuccessfulSubtask

import java.util.concurrent.StructuredTaskScope.Joiner

/** Adapter that converts Java
  * [[java.util.concurrent.StructuredTaskScope.Joiner Joiner]] to Scala
  * [[pl.andrzejressel.joiners4s.joiners.ScalaJoiner ScalaJoiner]].
  * @param inner
  *   The Java Joiner to be adapted.
  * @tparam T
  *   The result type of subtask forked in the scope.
  * @tparam R
  *   the type of the result returned by the join() method
  * @tparam R_X
  *   the type of the exception thrown by the join() method
  */
class ScalaJoinerAdapter[T, R, R_X <: Exception](inner: Joiner[T, R, R_X])
    extends ScalaJoiner[T, R, R_X] {

  override def onFork(
      subtask: ScalaJoiner.StartingSubtask[T]
  ): JoinerResult = {
    val javaResult = inner.onFork(subtask.original)
    val result = JoinerResult.fromJava(javaResult)
    result
  }

  override def onComplete(
      subtask: SuccessfulSubtask[T] | FailedSubtask[T]
  ): JoinerResult = {
    val javaResult = inner.onComplete(subtask.original)
    val result = JoinerResult.fromJava(javaResult)
    result
  }

  override def result(): R = inner.result()

  override def timeout(): R = inner.timeout()
}
