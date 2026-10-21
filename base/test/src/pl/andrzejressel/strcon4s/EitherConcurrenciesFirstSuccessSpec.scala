package pl.andrzejressel.strcon4s

import munit.FunSuite

class EitherConcurrenciesFirstSuccessSpec extends FunSuite {

  test("firstSuccess returns the first successful result") {
    val result = EitherConcurrencies.firstSuccess(
      { Thread.sleep(Int.MaxValue); Left("failure1") },
      Right(42)
    )
    assertEquals(result, Right(42))
  }

  test("firstSuccess returns the second successful result if the first fails") {
    val result = EitherConcurrencies.firstSuccess(
      Left("failure1"),
      { Thread.sleep(100); Right(42) }
    )
    assertEquals(result, Right(42))
  }

  test("failures of different types are returned as a tuple") {
    val result = EitherConcurrencies.firstSuccess(
      Left("failure1"),
      Left(42)
    )
    assertEquals(result, Left(("failure1", 42)))
  }

  test(
    "firstSuccess throws an exception if one computation throws an exception and the other computation fails"
  ) {
    val expectedException = new RuntimeException("failure2")
    val ex = intercept[RuntimeException] {
      EitherConcurrencies.firstSuccess(
        {
          Thread.sleep(Int.MaxValue)
          Left("abc")
        }, {
          throw expectedException
        }
      )
    }
    assertEquals(ex, expectedException)
  }

  test(
    "firstSuccess throws an exception if any computation throws an exception and the other computation succeeds"
  ) {
    val expectedException = new RuntimeException("failure1")
    val ex = intercept[RuntimeException] {
      EitherConcurrencies.firstSuccess(
        {
          throw expectedException
        }, {
          Thread.sleep(Int.MaxValue)
          Right("abc")
        }
      )
    }
    assertEquals(ex, expectedException)
  }

  test(
    "firstSuccess throws an exception if exception is thrown after Left"
  ) {
    val expectedException = new RuntimeException("failure1")
    val ex = intercept[RuntimeException] {
      EitherConcurrencies.firstSuccess(
        {
          Thread.sleep(100)
          throw expectedException
        }, {
          Left("abc")
        }
      )
    }
    assertEquals(ex, expectedException)
  }

  test("firstSuccess returns value when exception is thrown after Right") {
    val expectedException = new RuntimeException("failure1")
    val result = EitherConcurrencies.firstSuccess(
      {
        Thread.sleep(100)
        throw expectedException
      }, {
        Right("abc")
      }
    )
    assertEquals(result, Right("abc"))
  }

  test("errors should be in correct order") {
    val result = EitherConcurrencies.firstSuccess(
      Left("failure1"),
      { Thread.sleep(200); Left(42) },
      Left(false)
    )
    assertEquals(result, Left(("failure1", 42, false)))
  }

}
