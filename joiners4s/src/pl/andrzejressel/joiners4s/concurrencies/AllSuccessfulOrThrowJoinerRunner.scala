package pl.andrzejressel.joiners4s.concurrencies

import pl.andrzejressel.joiners.JoinerRunner
import pl.andrzejressel.joiners4s.Extensions.{toCallable, toScala}

import java.util.concurrent.Callable
import scala.jdk.CollectionConverters.*
import java.util.List as JList

trait AllSuccessfulOrThrowJoinerRunner {

  /** Runs the given functions in parallel and returns a tuple containing the
    * results if both complete successfully. If any callable fails, it throws an
    * exception.
    * @param f1
    *   First function to run
    * @param f2
    *   Second function to run
    * @tparam T1
    *   Type of the result of the first function
    * @tparam T2
    *   Type of the result of the second function
    * @return
    *   A tuple containing the results of both functions
    * @throws java.util.concurrent.ExecutionException
    *   Wrapper exception over the exception thrown by the callable that failed
    * @throws InterruptedException
    *   If the current thread was interrupted while waiting for the callables to
    */
  def apply[T1, T2](f1: => T1, f2: => T2): (T1, T2) = {
    JoinerRunner.jdk.allSuccessfulOrThrow
      .run[T1, T2](
        (() => f1).toCallable,
        (() => f2).toCallable
      )
      .toScala
  }

  /** Runs the given functions in parallel and returns a list containing the
    * results if all complete successfully. If any callable fails, it throws an
    * exception.
    * @param functions
    *   List of functions to run in parallel
    * @tparam T
    *   Type of the result of the functions
    * @return
    *   A list containing the results of the functions
    * @throws java.util.concurrent.ExecutionException
    *   Wrapper exception over the exception thrown by the callable that failed
    * @throws InterruptedException
    *   If the current thread was interrupted while waiting for the callables to
    *   complete
    */
  def apply[T](functions: Seq[() => T]): Seq[T] = {
    val callables: JList[Callable[T]] = functions.map(_.toCallable).asJava
    JoinerRunner.jdk.allSuccessfulOrThrow.run(callables).asScala.toSeq
  }

}
