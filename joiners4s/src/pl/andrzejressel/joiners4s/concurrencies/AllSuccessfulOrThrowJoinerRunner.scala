package pl.andrzejressel.joiners4s.concurrencies

import pl.andrzejressel.joiners.JoinerRunner
import pl.andrzejressel.joiners4s.Extensions.toCallable
import pl.andrzejressel.joiners4s.Extensions.toScala

import java.util.List as JList
import java.util.concurrent.Callable
import scala.jdk.CollectionConverters.*

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

  def apply[T1, T2, T3](
      f1: => T1,
      f2: => T2,
      f3: => T3
  ): (T1, T2, T3) = {
    val result = JoinerRunner.jdk.allSuccessfulOrThrow.run[T1, T2, T3](
      (() => f1).toCallable,
      (() => f2).toCallable,
      (() => f3).toCallable
    )
    (result.value0(), result.value1(), result.value2())
  }

  def apply[T1, T2, T3, T4](
      f1: => T1,
      f2: => T2,
      f3: => T3,
      f4: => T4
  ): (T1, T2, T3, T4) = {
    val result = JoinerRunner.jdk.allSuccessfulOrThrow.run[T1, T2, T3, T4](
      (() => f1).toCallable,
      (() => f2).toCallable,
      (() => f3).toCallable,
      (() => f4).toCallable
    )
    (result.value0(), result.value1(), result.value2(), result.value3())
  }

  def apply[T1, T2, T3, T4, T5](
      f1: => T1,
      f2: => T2,
      f3: => T3,
      f4: => T4,
      f5: => T5
  ): (T1, T2, T3, T4, T5) = {
    val result =
      JoinerRunner.jdk.allSuccessfulOrThrow.run[T1, T2, T3, T4, T5](
        (() => f1).toCallable,
        (() => f2).toCallable,
        (() => f3).toCallable,
        (() => f4).toCallable,
        (() => f5).toCallable
      )
    (
      result.value0(),
      result.value1(),
      result.value2(),
      result.value3(),
      result.value4()
    )
  }

  def apply[T1, T2, T3, T4, T5, T6](
      f1: => T1,
      f2: => T2,
      f3: => T3,
      f4: => T4,
      f5: => T5,
      f6: => T6
  ): (T1, T2, T3, T4, T5, T6) = {
    val result =
      JoinerRunner.jdk.allSuccessfulOrThrow.run[T1, T2, T3, T4, T5, T6](
        (() => f1).toCallable,
        (() => f2).toCallable,
        (() => f3).toCallable,
        (() => f4).toCallable,
        (() => f5).toCallable,
        (() => f6).toCallable
      )
    (
      result.value0(),
      result.value1(),
      result.value2(),
      result.value3(),
      result.value4(),
      result.value5()
    )
  }

  def apply[T1, T2, T3, T4, T5, T6, T7](
      f1: => T1,
      f2: => T2,
      f3: => T3,
      f4: => T4,
      f5: => T5,
      f6: => T6,
      f7: => T7
  ): (T1, T2, T3, T4, T5, T6, T7) = {
    val result =
      JoinerRunner.jdk.allSuccessfulOrThrow.run[T1, T2, T3, T4, T5, T6, T7](
        (() => f1).toCallable,
        (() => f2).toCallable,
        (() => f3).toCallable,
        (() => f4).toCallable,
        (() => f5).toCallable,
        (() => f6).toCallable,
        (() => f7).toCallable
      )
    (
      result.value0(),
      result.value1(),
      result.value2(),
      result.value3(),
      result.value4(),
      result.value5(),
      result.value6()
    )
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8](
      f1: => T1,
      f2: => T2,
      f3: => T3,
      f4: => T4,
      f5: => T5,
      f6: => T6,
      f7: => T7,
      f8: => T8
  ): (T1, T2, T3, T4, T5, T6, T7, T8) = {
    val result =
      JoinerRunner.jdk.allSuccessfulOrThrow.run[
        T1,
        T2,
        T3,
        T4,
        T5,
        T6,
        T7,
        T8
      ](
        (() => f1).toCallable,
        (() => f2).toCallable,
        (() => f3).toCallable,
        (() => f4).toCallable,
        (() => f5).toCallable,
        (() => f6).toCallable,
        (() => f7).toCallable,
        (() => f8).toCallable
      )
    (
      result.value0(),
      result.value1(),
      result.value2(),
      result.value3(),
      result.value4(),
      result.value5(),
      result.value6(),
      result.value7()
    )
  }

  def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9](
      f1: => T1,
      f2: => T2,
      f3: => T3,
      f4: => T4,
      f5: => T5,
      f6: => T6,
      f7: => T7,
      f8: => T8,
      f9: => T9
  ): (T1, T2, T3, T4, T5, T6, T7, T8, T9) = {
    val result =
      JoinerRunner.jdk.allSuccessfulOrThrow.run[
        T1,
        T2,
        T3,
        T4,
        T5,
        T6,
        T7,
        T8,
        T9
      ](
        (() => f1).toCallable,
        (() => f2).toCallable,
        (() => f3).toCallable,
        (() => f4).toCallable,
        (() => f5).toCallable,
        (() => f6).toCallable,
        (() => f7).toCallable,
        (() => f8).toCallable,
        (() => f9).toCallable
      )
    (
      result.value0(),
      result.value1(),
      result.value2(),
      result.value3(),
      result.value4(),
      result.value5(),
      result.value6(),
      result.value7(),
      result.value8()
    )
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
