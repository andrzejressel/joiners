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

  test("Three callables - allSuccessfulOrThrow returns all results") {
    assertEquals(
      JoinerRunner.JDK.AllSuccessfulOrThrow(1, "2", 3L),
      (1, "2", 3L)
    )
  }

  test("Three callables - allSuccessfulOrThrow propagates failures") {
    val expectedException = new RuntimeException("failure")
    val ex = intercept[ExecutionException] {
      JoinerRunner.JDK.AllSuccessfulOrThrow(1, 2, throw expectedException)
    }
    assertEquals(ex.getCause, expectedException)
  }

  test("Four callables - allSuccessfulOrThrow returns all results") {
    assertEquals(
      JoinerRunner.JDK.AllSuccessfulOrThrow(1, "2", 3L, 4.0),
      (1, "2", 3L, 4.0)
    )
  }

  test("Four callables - allSuccessfulOrThrow propagates failures") {
    val expectedException = new RuntimeException("failure")
    val ex = intercept[ExecutionException] {
      JoinerRunner.JDK.AllSuccessfulOrThrow(1, 2, 3, throw expectedException)
    }
    assertEquals(ex.getCause, expectedException)
  }

  test("Five callables - allSuccessfulOrThrow returns all results") {
    assertEquals(
      JoinerRunner.JDK.AllSuccessfulOrThrow(1, "2", 3L, 4.0, true),
      (1, "2", 3L, 4.0, true)
    )
  }

  test("Five callables - allSuccessfulOrThrow propagates failures") {
    val expectedException = new RuntimeException("failure")
    val ex = intercept[ExecutionException] {
      JoinerRunner.JDK.AllSuccessfulOrThrow(1, 2, 3, 4, throw expectedException)
    }
    assertEquals(ex.getCause, expectedException)
  }

  test("Six callables - allSuccessfulOrThrow returns all results") {
    assertEquals(
      JoinerRunner.JDK.AllSuccessfulOrThrow(1, "2", 3L, 4.0, true, '6'),
      (1, "2", 3L, 4.0, true, '6')
    )
  }

  test("Six callables - allSuccessfulOrThrow propagates failures") {
    val expectedException = new RuntimeException("failure")
    val ex = intercept[ExecutionException] {
      JoinerRunner.JDK.AllSuccessfulOrThrow(
        1,
        2,
        3,
        4,
        5,
        throw expectedException
      )
    }
    assertEquals(ex.getCause, expectedException)
  }

  test("Seven callables - allSuccessfulOrThrow returns all results") {
    assertEquals(
      JoinerRunner.JDK.AllSuccessfulOrThrow(
        1,
        "2",
        3L,
        4.0,
        true,
        '6',
        7.toByte
      ),
      (1, "2", 3L, 4.0, true, '6', 7.toByte)
    )
  }

  test("Seven callables - allSuccessfulOrThrow propagates failures") {
    val expectedException = new RuntimeException("failure")
    val ex = intercept[ExecutionException] {
      JoinerRunner.JDK.AllSuccessfulOrThrow(
        1,
        2,
        3,
        4,
        5,
        6,
        throw expectedException
      )
    }
    assertEquals(ex.getCause, expectedException)
  }

  test("Eight callables - allSuccessfulOrThrow returns all results") {
    assertEquals(
      JoinerRunner.JDK.AllSuccessfulOrThrow(
        1,
        "2",
        3L,
        4.0,
        true,
        '6',
        7.toByte,
        8.toShort
      ),
      (1, "2", 3L, 4.0, true, '6', 7.toByte, 8.toShort)
    )
  }

  test("Eight callables - allSuccessfulOrThrow propagates failures") {
    val expectedException = new RuntimeException("failure")
    val ex = intercept[ExecutionException] {
      JoinerRunner.JDK.AllSuccessfulOrThrow(
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        throw expectedException
      )
    }
    assertEquals(ex.getCause, expectedException)
  }

  test("Nine callables - allSuccessfulOrThrow returns all successful results") {
    val result = JoinerRunner.JDK.AllSuccessfulOrThrow(
      1,
      "2",
      3L,
      4.0,
      true,
      '6',
      7.toByte,
      8.toShort,
      9
    )
    assertEquals(result, (1, "2", 3L, 4.0, true, '6', 7.toByte, 8.toShort, 9))
  }

  test("Nine callables - allSuccessfulOrThrow throws if a computation fails") {
    val expectedException = new RuntimeException("failure")
    val ex = intercept[ExecutionException] {
      JoinerRunner.JDK.AllSuccessfulOrThrow(
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
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
