package pl.andrzejressel.joiners4s.joiners

import munit.FunSuite
import pl.andrzejressel.joiners4s.JoinerRunner

import scala.concurrent.ExecutionException

class AllSuccessfulOrThrowJoinerRunnerSpec extends FunSuite {

  test("Two callables - allSuccessfulOrThrow returns all successful results") {
    val result = JoinerRunner.JDK.AllSuccessfulOrThrow(
      1,
      2
    )
    assertEquals(result, (1, 2))
  }

  test(
    "Two callables - allSuccessfulOrThrow throws an exception if any computation fails"
  ) {
    val expectedException = new RuntimeException("failure")
    val ex = intercept[ExecutionException] {
      JoinerRunner.JDK.AllSuccessfulOrThrow(
        1,
        throw expectedException
      )
    }
    assertEquals(ex.getCause, expectedException)
  }

  test("allSuccessfulOrThrow returns all successful results") {
    val result = JoinerRunner.JDK.AllSuccessfulOrThrow(
      Seq(
        () => 1,
        () => 2,
        () => 3
      )
    )
    assertEquals(result, List(1, 2, 3))
  }

  test("allSuccessfulOrThrow throws an exception if any computation fails") {
    val expectedException = new RuntimeException("failure")
    val ex = intercept[ExecutionException] {
      JoinerRunner.JDK.AllSuccessfulOrThrow(
        Seq(
          () => 1,
          () => throw expectedException,
          () => 3
        )
      )
    }
    assertEquals(ex.getCause, expectedException)
  }

}
