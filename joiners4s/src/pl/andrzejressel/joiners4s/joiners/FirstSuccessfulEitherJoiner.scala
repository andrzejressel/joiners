package pl.andrzejressel.joiners4s.joiners

import pl.andrzejressel.joiners4s.joiners.ScalaJoiner
import pl.andrzejressel.joiners4s.joiners.ScalaJoiner.FailedSubtask
import pl.andrzejressel.joiners4s.joiners.ScalaJoiner.JoinerResult
import pl.andrzejressel.joiners4s.joiners.ScalaJoiner.ScalaSubtask
import pl.andrzejressel.joiners4s.joiners.ScalaJoiner.SuccessfulSubtask
import pl.andrzejressel.joiners4s.joiners.ScalaJoinerAdapter

import java.util.concurrent.StructuredTaskScope
import java.util.concurrent.StructuredTaskScope.CancelledByTimeoutException
import java.util.concurrent.StructuredTaskScope.Subtask.State
import scala.jdk.CollectionConverters.*

/** Joiner that waits for the first successful subtask (in the context of Either
  * the first right value) and returns it. If all subtasks fail (in the context
  * of Either all left values), it returns a sequence of all the left values. If
  * any subtask throws an exception, it will throw that exception.
  *
  * @tparam LEFT
  *   The type of the left value in the Either. Represents the failure case.
  * @tparam RIGHT
  *   The type of the right value in the Either. Represents the success case.
  */
private[joiners4s] class FirstSuccessfulEitherJoiner[LEFT, RIGHT]
    extends ScalaJoiner[
      Either[LEFT, RIGHT],
      Either[Seq[LEFT], RIGHT],
      Exception
    ] {

  private type SubtaskType = ScalaSubtask[Either[LEFT, RIGHT]]

  private val joiner =
    ScalaJoinerAdapter(
      StructuredTaskScope.Joiner.allUntil[Either[LEFT, RIGHT]](subtask => {
        subtask.state() == State.FAILED || (subtask
          .state() == State.SUCCESS && subtask.get().isRight)
      })
    )

  override def onFork(
      subtask: ScalaJoiner.StartingSubtask[Either[LEFT, RIGHT]]
  ): JoinerResult = {
    joiner.onFork(subtask)
  }

  override def onComplete(
      subtask: SuccessfulSubtask[Either[LEFT, RIGHT]] |
        FailedSubtask[Either[LEFT, RIGHT]]
  ): JoinerResult = {
    joiner.onComplete(subtask)
  }

  override def result(): Either[Seq[LEFT], RIGHT] = {
    val javaSubtasks =
      joiner.result().asScala.map(ScalaJoiner.toScalaSubtask).toSeq
    failedWithException(javaSubtasks) match {
      case Some(exception) => throw exception
      case None            => ()
    }
    getFirstRight(javaSubtasks) match {
      case Some(value) => return Right(value)
      case None        => ()
    }
    Left(getAllLefts(javaSubtasks))
  }

  private def failedWithException(
      subtasks: Seq[SubtaskType]
  ): Option[Throwable] = {
    subtasks.collectFirst { case FailedSubtask(ex, _) =>
      ex
    }
  }

  private def getFirstRight(subtasks: Seq[SubtaskType]): Option[RIGHT] = {
    subtasks.collectFirst { case SuccessfulSubtask(Right(value), _) =>
      value
    }
  }

  private def getAllLefts(subtasks: Seq[SubtaskType]): Seq[LEFT] = {
    subtasks.map {
      case SuccessfulSubtask(Left(value), _) => value
      case other                             =>
        throw new IllegalStateException(
          s"Unexpected subtask state: $other"
        )
    }
  }

  override def timeout(): Either[Seq[LEFT], RIGHT] = {
    throw new CancelledByTimeoutException()
  }

}
