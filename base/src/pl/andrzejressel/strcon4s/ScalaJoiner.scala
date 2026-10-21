package pl.andrzejressel.strcon4s

import pl.andrzejressel.strcon4s.ScalaJoiner.JoinerResult
import pl.andrzejressel.strcon4s.ScalaJoiner.SuccessfulSubtask
import pl.andrzejressel.strcon4s.ScalaJoiner.toScalaSubtask

import java.util.concurrent.StructuredTaskScope.Joiner
import java.util.concurrent.StructuredTaskScope.Subtask

/** A Scala-friendly wrapper around the Java Joiner interface, providing a more
  * idiomatic way to handle subtasks in Scala.
  *
  * @tparam T
  *   The result type of subtask forked in the scope.
  * @tparam R
  *   the type of the result returned byt the join() method
  * @tparam R_X
  *   the type of the exception thrown by the join() method
  */
trait ScalaJoiner[T, R, R_X <: Exception] extends Joiner[T, R, R_X] {

  def onFork(subtask: ScalaJoiner.StartingSubtask[T]): JoinerResult =
    JoinerResult.CONTINUE

  def onComplete(
      subtask: SuccessfulSubtask[T] | ScalaJoiner.FailedSubtask[T]
  ): JoinerResult = JoinerResult.CONTINUE

  override final def onFork(subtask: Subtask[T]): Boolean = {
    toScalaSubtask(subtask) match {
      case s @ ScalaJoiner.StartingSubtask(_)      => onFork(s).toJava
      case ScalaJoiner.SuccessfulSubtask(value, _) =>
        throw new IllegalStateException(
          s"Subtask completed before onFork was called. Value: $value"
        )
      case ScalaJoiner.FailedSubtask(exception, _) =>
        throw new IllegalStateException(
          s"Subtask failed before onFork was called. Exception: $exception"
        )
    }
  }

  override final def onComplete(subtask: Subtask[T]): Boolean = {
    toScalaSubtask(subtask) match {
      case ScalaJoiner.StartingSubtask(_) =>
        throw new IllegalStateException(
          "Subtask is still starting when onComplete was called."
        )
      case s @ (ScalaJoiner.SuccessfulSubtask(_, _) |
          ScalaJoiner.FailedSubtask(_, _)) =>
        onComplete(s).toJava
    }
  }

}

object ScalaJoiner {

  sealed trait ScalaSubtask[T] {
    def original: Subtask[T]
  }

  case class SuccessfulSubtask[T](value: T, original: Subtask[T])
      extends ScalaSubtask[T]

  case class FailedSubtask[T](exception: Throwable, original: Subtask[T])
      extends ScalaSubtask[T]

  case class StartingSubtask[T](original: Subtask[T]) extends ScalaSubtask[T]

  private def toScalaSubtask[T](
      subtask: Subtask[T]
  ): SuccessfulSubtask[T] | FailedSubtask[T] | StartingSubtask[T] = {
    subtask.state() match {
      case Subtask.State.UNAVAILABLE => StartingSubtask(subtask)
      case Subtask.State.SUCCESS => SuccessfulSubtask(subtask.get(), subtask)
      case Subtask.State.FAILED  => FailedSubtask(subtask.exception(), subtask)
    }
  }

  enum JoinerResult {
    case CONTINUE
    case CANCEL_SCOPE

    def toJava: Boolean = this match {
      case CONTINUE     => false
      case CANCEL_SCOPE => true
    }

  }

}
