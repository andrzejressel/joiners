package pl.andrzejressel.joiners4s.concurrencies

import pl.andrzejressel.joiners4s.Extensions.*
import pl.andrzejressel.joiners4s.joiners.FirstSuccessfulEitherJoiner

import java.util.concurrent.StructuredTaskScope
import scala.util.Using

private[joiners4s] trait EitherFirstSuccessful {

  def apply[FAILURE, SUCCESS](
      functions: Seq[() => Either[FAILURE, SUCCESS]]
  ): Either[Seq[FAILURE], SUCCESS] = {

    Using(
      StructuredTaskScope.open(
        FirstSuccessfulEitherJoiner[FAILURE, SUCCESS]
      )
    ) { scope =>
      functions.foreach(f => {
        scope.fork(f())
      })
      scope.join()
    }.get
  }

  def apply[SUC1, FAIL1, SUC2, FAIL2](
      first: => Either[FAIL1, SUC1],
      second: => Either[FAIL2, SUC2]
  ): Either[(FAIL1, FAIL2), SUC1 | SUC2] = {
    apply[FAIL1 | FAIL2, SUC1 | SUC2](
      Seq(() => first, () => second)
    ).left.map {
      case Seq(f1, f2) => (f1.asInstanceOf[FAIL1], f2.asInstanceOf[FAIL2])
      case seq         =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [2]"
        )
    }
  }

  def apply[SUC1, FAIL1, SUC2, FAIL2, SUC3, FAIL3](
      first: => Either[FAIL1, SUC1],
      second: => Either[FAIL2, SUC2],
      third: => Either[FAIL3, SUC3]
  ): Either[(FAIL1, FAIL2, FAIL3), SUC1 | SUC2 | SUC3] = {
    apply[FAIL1 | FAIL2 | FAIL3, SUC1 | SUC2 | SUC3](
      Seq(() => first, () => second, () => third)
    ).left.map {
      case Seq(f1, f2, f3) =>
        (f1.asInstanceOf[FAIL1], f2.asInstanceOf[FAIL2], f3.asInstanceOf[FAIL3])
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [3]"
        )
    }
  }

  def apply[SUC1, FAIL1, SUC2, FAIL2, SUC3, FAIL3, SUC4, FAIL4](
      first: => Either[FAIL1, SUC1],
      second: => Either[FAIL2, SUC2],
      third: => Either[FAIL3, SUC3],
      fourth: => Either[FAIL4, SUC4]
  ): Either[(FAIL1, FAIL2, FAIL3, FAIL4), SUC1 | SUC2 | SUC3 | SUC4] = {
    apply[FAIL1 | FAIL2 | FAIL3 | FAIL4, SUC1 | SUC2 | SUC3 | SUC4](
      Seq(() => first, () => second, () => third, () => fourth)
    ).left.map {
      case Seq(f1, f2, f3, f4) =>
        (
          f1.asInstanceOf[FAIL1],
          f2.asInstanceOf[FAIL2],
          f3.asInstanceOf[FAIL3],
          f4.asInstanceOf[FAIL4]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [4]"
        )
    }
  }

  def apply[
      SUC1,
      FAIL1,
      SUC2,
      FAIL2,
      SUC3,
      FAIL3,
      SUC4,
      FAIL4,
      SUC5,
      FAIL5
  ](
      first: => Either[FAIL1, SUC1],
      second: => Either[FAIL2, SUC2],
      third: => Either[FAIL3, SUC3],
      fourth: => Either[FAIL4, SUC4],
      fifth: => Either[FAIL5, SUC5]
  ): Either[
    (FAIL1, FAIL2, FAIL3, FAIL4, FAIL5),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5
  ] = {
    apply[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5
    ](
      Seq(() => first, () => second, () => third, () => fourth, () => fifth)
    ).left.map {
      case Seq(f1, f2, f3, f4, f5) =>
        (
          f1.asInstanceOf[FAIL1],
          f2.asInstanceOf[FAIL2],
          f3.asInstanceOf[FAIL3],
          f4.asInstanceOf[FAIL4],
          f5.asInstanceOf[FAIL5]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [5]"
        )
    }
  }

  def apply[
      SUC1,
      FAIL1,
      SUC2,
      FAIL2,
      SUC3,
      FAIL3,
      SUC4,
      FAIL4,
      SUC5,
      FAIL5,
      SUC6,
      FAIL6
  ](
      first: => Either[FAIL1, SUC1],
      second: => Either[FAIL2, SUC2],
      third: => Either[FAIL3, SUC3],
      fourth: => Either[FAIL4, SUC4],
      fifth: => Either[FAIL5, SUC5],
      sixth: => Either[FAIL6, SUC6]
  ): Either[
    (FAIL1, FAIL2, FAIL3, FAIL4, FAIL5, FAIL6),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6
  ] = {
    apply[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5 | FAIL6,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6
    ](
      Seq(
        () => first,
        () => second,
        () => third,
        () => fourth,
        () => fifth,
        () => sixth
      )
    ).left.map {
      case Seq(f1, f2, f3, f4, f5, f6) =>
        (
          f1.asInstanceOf[FAIL1],
          f2.asInstanceOf[FAIL2],
          f3.asInstanceOf[FAIL3],
          f4.asInstanceOf[FAIL4],
          f5.asInstanceOf[FAIL5],
          f6.asInstanceOf[FAIL6]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [6]"
        )
    }
  }

  def apply[
      SUC1,
      FAIL1,
      SUC2,
      FAIL2,
      SUC3,
      FAIL3,
      SUC4,
      FAIL4,
      SUC5,
      FAIL5,
      SUC6,
      FAIL6,
      SUC7,
      FAIL7
  ](
      first: => Either[FAIL1, SUC1],
      second: => Either[FAIL2, SUC2],
      third: => Either[FAIL3, SUC3],
      fourth: => Either[FAIL4, SUC4],
      fifth: => Either[FAIL5, SUC5],
      sixth: => Either[FAIL6, SUC6],
      seventh: => Either[FAIL7, SUC7]
  ): Either[
    (FAIL1, FAIL2, FAIL3, FAIL4, FAIL5, FAIL6, FAIL7),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7
  ] = {
    apply[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5 | FAIL6 | FAIL7,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7
    ](
      Seq(
        () => first,
        () => second,
        () => third,
        () => fourth,
        () => fifth,
        () => sixth,
        () => seventh
      )
    ).left.map {
      case Seq(f1, f2, f3, f4, f5, f6, f7) =>
        (
          f1.asInstanceOf[FAIL1],
          f2.asInstanceOf[FAIL2],
          f3.asInstanceOf[FAIL3],
          f4.asInstanceOf[FAIL4],
          f5.asInstanceOf[FAIL5],
          f6.asInstanceOf[FAIL6],
          f7.asInstanceOf[FAIL7]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [7]"
        )
    }
  }

  def apply[
      SUC1,
      FAIL1,
      SUC2,
      FAIL2,
      SUC3,
      FAIL3,
      SUC4,
      FAIL4,
      SUC5,
      FAIL5,
      SUC6,
      FAIL6,
      SUC7,
      FAIL7,
      SUC8,
      FAIL8
  ](
      first: => Either[FAIL1, SUC1],
      second: => Either[FAIL2, SUC2],
      third: => Either[FAIL3, SUC3],
      fourth: => Either[FAIL4, SUC4],
      fifth: => Either[FAIL5, SUC5],
      sixth: => Either[FAIL6, SUC6],
      seventh: => Either[FAIL7, SUC7],
      eighth: => Either[FAIL8, SUC8]
  ): Either[
    (FAIL1, FAIL2, FAIL3, FAIL4, FAIL5, FAIL6, FAIL7, FAIL8),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8
  ] = {
    apply[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5 | FAIL6 | FAIL7 | FAIL8,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8
    ](
      Seq(
        () => first,
        () => second,
        () => third,
        () => fourth,
        () => fifth,
        () => sixth,
        () => seventh,
        () => eighth
      )
    ).left.map {
      case Seq(f1, f2, f3, f4, f5, f6, f7, f8) =>
        (
          f1.asInstanceOf[FAIL1],
          f2.asInstanceOf[FAIL2],
          f3.asInstanceOf[FAIL3],
          f4.asInstanceOf[FAIL4],
          f5.asInstanceOf[FAIL5],
          f6.asInstanceOf[FAIL6],
          f7.asInstanceOf[FAIL7],
          f8.asInstanceOf[FAIL8]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [8]"
        )
    }
  }

  def apply[
      SUC1,
      FAIL1,
      SUC2,
      FAIL2,
      SUC3,
      FAIL3,
      SUC4,
      FAIL4,
      SUC5,
      FAIL5,
      SUC6,
      FAIL6,
      SUC7,
      FAIL7,
      SUC8,
      FAIL8,
      SUC9,
      FAIL9
  ](
      first: => Either[FAIL1, SUC1],
      second: => Either[FAIL2, SUC2],
      third: => Either[FAIL3, SUC3],
      fourth: => Either[FAIL4, SUC4],
      fifth: => Either[FAIL5, SUC5],
      sixth: => Either[FAIL6, SUC6],
      seventh: => Either[FAIL7, SUC7],
      eighth: => Either[FAIL8, SUC8],
      ninth: => Either[FAIL9, SUC9]
  ): Either[
    (FAIL1, FAIL2, FAIL3, FAIL4, FAIL5, FAIL6, FAIL7, FAIL8, FAIL9),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9
  ] = {
    apply[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5 | FAIL6 | FAIL7 | FAIL8 | FAIL9,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9
    ](
      Seq(
        () => first,
        () => second,
        () => third,
        () => fourth,
        () => fifth,
        () => sixth,
        () => seventh,
        () => eighth,
        () => ninth
      )
    ).left.map {
      case Seq(f1, f2, f3, f4, f5, f6, f7, f8, f9) =>
        (
          f1.asInstanceOf[FAIL1],
          f2.asInstanceOf[FAIL2],
          f3.asInstanceOf[FAIL3],
          f4.asInstanceOf[FAIL4],
          f5.asInstanceOf[FAIL5],
          f6.asInstanceOf[FAIL6],
          f7.asInstanceOf[FAIL7],
          f8.asInstanceOf[FAIL8],
          f9.asInstanceOf[FAIL9]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [9]"
        )
    }
  }

  def apply[
      SUC1,
      FAIL1,
      SUC2,
      FAIL2,
      SUC3,
      FAIL3,
      SUC4,
      FAIL4,
      SUC5,
      FAIL5,
      SUC6,
      FAIL6,
      SUC7,
      FAIL7,
      SUC8,
      FAIL8,
      SUC9,
      FAIL9,
      SUC10,
      FAIL10
  ](
      first: => Either[FAIL1, SUC1],
      second: => Either[FAIL2, SUC2],
      third: => Either[FAIL3, SUC3],
      fourth: => Either[FAIL4, SUC4],
      fifth: => Either[FAIL5, SUC5],
      sixth: => Either[FAIL6, SUC6],
      seventh: => Either[FAIL7, SUC7],
      eighth: => Either[FAIL8, SUC8],
      ninth: => Either[FAIL9, SUC9],
      tenth: => Either[FAIL10, SUC10]
  ): Either[
    (FAIL1, FAIL2, FAIL3, FAIL4, FAIL5, FAIL6, FAIL7, FAIL8, FAIL9, FAIL10),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10
  ] = {
    apply[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5 | FAIL6 | FAIL7 | FAIL8 | FAIL9 | FAIL10,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10
    ](
      Seq(
        () => first,
        () => second,
        () => third,
        () => fourth,
        () => fifth,
        () => sixth,
        () => seventh,
        () => eighth,
        () => ninth,
        () => tenth
      )
    ).left.map {
      case Seq(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10) =>
        (
          f1.asInstanceOf[FAIL1],
          f2.asInstanceOf[FAIL2],
          f3.asInstanceOf[FAIL3],
          f4.asInstanceOf[FAIL4],
          f5.asInstanceOf[FAIL5],
          f6.asInstanceOf[FAIL6],
          f7.asInstanceOf[FAIL7],
          f8.asInstanceOf[FAIL8],
          f9.asInstanceOf[FAIL9],
          f10.asInstanceOf[FAIL10]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [10]"
        )
    }
  }
}
