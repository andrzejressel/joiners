package pl.andrzejressel.joiners4s.joiners

import pl.andrzejressel.joiners4s.AtomicState
import pl.andrzejressel.joiners4s.ConcurrentLinkedHashMap
import pl.andrzejressel.joiners4s.Extensions.throwOnNull
import pl.andrzejressel.joiners4s.ScalaJoiner
import pl.andrzejressel.joiners4s.ScalaJoiner.FailedSubtask
import pl.andrzejressel.joiners4s.ScalaJoiner.JoinerResult
import pl.andrzejressel.joiners4s.ScalaJoiner.JoinerResult.CANCEL_SCOPE
import pl.andrzejressel.joiners4s.ScalaJoiner.JoinerResult.CONTINUE
import pl.andrzejressel.joiners4s.ScalaJoiner.ScalaSubtask
import pl.andrzejressel.joiners4s.ScalaJoiner.SuccessfulSubtask

import java.util.concurrent.StructuredTaskScope.CancelledByTimeoutException
import java.util.concurrent.StructuredTaskScope.Subtask
import scala.collection.mutable

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

  private type JavaSubtaskType = Subtask[Either[LEFT, RIGHT]]
  private type SubtaskType = ScalaSubtask[Either[LEFT, RIGHT]]

  private var recentSubtaskId = 0;
  private val subtaskIdMap = mutable.HashMap[JavaSubtaskType, Int]()

  private val sm = new StateMachine()

  override def onFork(
      subtask: ScalaJoiner.StartingSubtask[Either[LEFT, RIGHT]]
  ): JoinerResult = {
    subtaskIdMap.put(subtask.original, recentSubtaskId)
    recentSubtaskId += 1
    sm.addSubtask(subtask)
  }

  override def onComplete(
      subtask: SuccessfulSubtask[Either[LEFT, RIGHT]] |
        FailedSubtask[Either[LEFT, RIGHT]]
  ): JoinerResult = {
    subtask match {
      case SuccessfulSubtask(either, _) =>
        either match {
          case Left(error) =>
            sm.addFailure(error, subtask)
          case Right(value) =>
            sm.addSuccess(value)
        }
      case FailedSubtask(exception, _) =>
        sm.addError(exception)
    }
  }

  override def result(): Either[Seq[LEFT], RIGHT] = {
    sm.s.get match {
      case StateMachineState.FirstSuccessObtained(value) =>
        Right(value)
      case StateMachineState.FailureObtained(map) =>
        Left(
          map
            .values()
            .map(
              _.throwOnNull(
                new IllegalStateException(
                  "Subtask failed but no error was recorded"
                )
              )
            )
        )
      case StateMachineState.ErrorOccurred(error) =>
        throw error
    }
  }

  override def timeout(): Either[Seq[LEFT], RIGHT] = {
    throw new CancelledByTimeoutException()
  }

  private class StateMachine(val s: AtomicState[StateMachineState]) {
    def this() = this(
      AtomicState(
        StateMachineState.FailureObtained(
          ConcurrentLinkedHashMap[JavaSubtaskType, LEFT | Null]()
        )
      )
    )

    def addSubtask(subtask: SubtaskType): JoinerResult = {
      s.update {
        case StateMachineState.FailureObtained(map) =>
          map.put(
            subtask.original,
            null
          ) // I know it's not idempotent and a side effect, but the map is threadsafe and we only add given element once
          (StateMachineState.FailureObtained(map), CONTINUE)
        case s: (StateMachineState.FirstSuccessObtained |
              StateMachineState.ErrorOccurred) =>
          (s, CANCEL_SCOPE)
      }
    }

    def addSuccess(value: RIGHT): JoinerResult = {
      s.update {
        case StateMachineState.FailureObtained(_) =>
          (StateMachineState.FirstSuccessObtained(value), CANCEL_SCOPE)
        case s: (StateMachineState.FirstSuccessObtained |
              StateMachineState.ErrorOccurred) =>
          (s, CANCEL_SCOPE)
      }
    }

    def addFailure(error: LEFT, subtask: SubtaskType): JoinerResult = {
      s.update {
        case StateMachineState.FailureObtained(map) =>
          map.put(
            subtask.original,
            error
          ) // I know it's not idempotent and a side effect, but the map is threadsafe and we only add given element once
          (StateMachineState.FailureObtained(map), CONTINUE)
        case s: (StateMachineState.FirstSuccessObtained |
              StateMachineState.ErrorOccurred) =>
          (s, CANCEL_SCOPE)
      }
    }

    def addError(error: Throwable): JoinerResult = {
      s.update {
        case StateMachineState.FailureObtained(_) =>
          (StateMachineState.ErrorOccurred(error), CANCEL_SCOPE)
        case s: (StateMachineState.FirstSuccessObtained |
              StateMachineState.ErrorOccurred) =>
          (s, CANCEL_SCOPE)
      }
    }
  }

  private sealed trait StateMachineState

  private object StateMachineState {
    case class FirstSuccessObtained(value: RIGHT) extends StateMachineState

    /** Represents the state that collects different failures from the subtasks.
      * Since the map is thread-safe and is **Linked** it preserves the order of
      * insertion Thanks to that the order of failures in the result will be the
      * same as the order of subtasks that were submitted to the scope.
      */
    case class FailureObtained(
        map: ConcurrentLinkedHashMap[JavaSubtaskType, LEFT | Null]
    ) extends StateMachineState

    case class ErrorOccurred(error: Throwable) extends StateMachineState
  }

}
