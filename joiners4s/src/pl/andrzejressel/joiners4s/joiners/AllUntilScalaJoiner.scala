package pl.andrzejressel.joiners4s.joiners

import pl.andrzejressel.joiners4s.joiners.ScalaJoiner.ScalaSubtask

import java.util.concurrent.StructuredTaskScope
import scala.jdk.CollectionConverters.*

class AllUntilScalaJoiner[T](predicate: ScalaSubtask[T] => Boolean)
    extends ScalaJoiner[T, Seq[ScalaSubtask[T]], Exception] {

  private val joiner = ScalaJoinerAdapter(
    StructuredTaskScope.Joiner.allUntil[T] { javaSubtask =>
      val scalaSubtask = ScalaJoiner.toScalaSubtask(javaSubtask)
      scalaSubtask match {
        case s @ ScalaJoiner.SuccessfulSubtask(_, _) => predicate(s)
        case s @ ScalaJoiner.FailedSubtask(_, _)     => predicate(s)
        case ScalaJoiner.StartingSubtask(original)   =>
          throw IllegalStateException(
            s"Subtask is in invalid state: ${original.state()}. Subtask: $original"
          )
      }
    }
  )

  override def onFork(
      subtask: ScalaJoiner.StartingSubtask[T]
  ): ScalaJoiner.JoinerResult = joiner.onFork(subtask)

  override def onComplete(
      subtask: ScalaJoiner.SuccessfulSubtask[T] | ScalaJoiner.FailedSubtask[T]
  ): ScalaJoiner.JoinerResult = joiner.onComplete(subtask)

  override def timeout(): Seq[ScalaSubtask[T]] = toScala(joiner.timeout())

  override def result(): Seq[ScalaSubtask[T]] = toScala(joiner.result())

  private def toScala(
      javaSubtasks: java.util.List[StructuredTaskScope.Subtask[T]]
  ): Seq[ScalaSubtask[T]] = {
    javaSubtasks.asScala.map(ScalaJoiner.toScalaSubtask).toSeq
  }

}
