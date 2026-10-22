package pl.andrzejressel.joiners.scala

import pl.andrzejressel.joiners.scala.Extensions.*
import pl.andrzejressel.joiners.scala.joiners.FirstSuccessfulEitherJoiner

import java.util.concurrent.StructuredTaskScope
import scala.util.Using

object EitherConcurrencies {

  def firstSuccess[FAILURE, SUCCESS](
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

  def firstSuccess[SUC1, FAIL1, SUC2, FAIL2](
      first: => Either[FAIL1, SUC1],
      second: => Either[FAIL2, SUC2]
  ): Either[(FAIL1, FAIL2), SUC1 | SUC2] = {
    firstSuccess[FAIL1 | FAIL2, SUC1 | SUC2](
      Seq(() => first, () => second)
    ).left.map {
      case Seq(f1, f2) => (f1.asInstanceOf[FAIL1], f2.asInstanceOf[FAIL2])
      case seq         =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [2]"
        )
    }
  }

  def firstSuccess[SUC1, FAIL1, SUC2, FAIL2, SUC3, FAIL3](
      first: => Either[FAIL1, SUC1],
      second: => Either[FAIL2, SUC2],
      third: => Either[FAIL3, SUC3]
  ): Either[(FAIL1, FAIL2, FAIL3), SUC1 | SUC2 | SUC3] = {
    firstSuccess[FAIL1 | FAIL2 | FAIL3, SUC1 | SUC2 | SUC3](
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

  def firstSuccess[SUC1, FAIL1, SUC2, FAIL2, SUC3, FAIL3, SUC4, FAIL4](
      first: => Either[FAIL1, SUC1],
      second: => Either[FAIL2, SUC2],
      third: => Either[FAIL3, SUC3],
      fourth: => Either[FAIL4, SUC4]
  ): Either[(FAIL1, FAIL2, FAIL3, FAIL4), SUC1 | SUC2 | SUC3 | SUC4] = {
    firstSuccess[FAIL1 | FAIL2 | FAIL3 | FAIL4, SUC1 | SUC2 | SUC3 | SUC4](
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

  def firstSuccess[
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
    firstSuccess[
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

  def firstSuccess[
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
    firstSuccess[
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

  def firstSuccess[
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
    firstSuccess[
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

  def firstSuccess[
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
    firstSuccess[
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

  def firstSuccess[
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
    firstSuccess[
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

  def firstSuccess[
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
    firstSuccess[
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

  def firstSuccess[
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
      FAIL10,
      SUC11,
      FAIL11
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
      tenth: => Either[FAIL10, SUC10],
      eleventh: => Either[FAIL11, SUC11]
  ): Either[
    (
        FAIL1,
        FAIL2,
        FAIL3,
        FAIL4,
        FAIL5,
        FAIL6,
        FAIL7,
        FAIL8,
        FAIL9,
        FAIL10,
        FAIL11
    ),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11
  ] = {
    firstSuccess[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5 | FAIL6 | FAIL7 | FAIL8 | FAIL9 | FAIL10 | FAIL11,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11
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
        () => tenth,
        () => eleventh
      )
    ).left.map {
      case Seq(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11) =>
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
          f10.asInstanceOf[FAIL10],
          f11.asInstanceOf[FAIL11]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [11]"
        )
    }
  }

  def firstSuccess[
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
      FAIL10,
      SUC11,
      FAIL11,
      SUC12,
      FAIL12
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
      tenth: => Either[FAIL10, SUC10],
      eleventh: => Either[FAIL11, SUC11],
      twelfth: => Either[FAIL12, SUC12]
  ): Either[
    (
        FAIL1,
        FAIL2,
        FAIL3,
        FAIL4,
        FAIL5,
        FAIL6,
        FAIL7,
        FAIL8,
        FAIL9,
        FAIL10,
        FAIL11,
        FAIL12
    ),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12
  ] = {
    firstSuccess[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5 | FAIL6 | FAIL7 | FAIL8 | FAIL9 | FAIL10 | FAIL11 | FAIL12,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12
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
        () => tenth,
        () => eleventh,
        () => twelfth
      )
    ).left.map {
      case Seq(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12) =>
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
          f10.asInstanceOf[FAIL10],
          f11.asInstanceOf[FAIL11],
          f12.asInstanceOf[FAIL12]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [12]"
        )
    }
  }

  def firstSuccess[
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
      FAIL10,
      SUC11,
      FAIL11,
      SUC12,
      FAIL12,
      SUC13,
      FAIL13
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
      tenth: => Either[FAIL10, SUC10],
      eleventh: => Either[FAIL11, SUC11],
      twelfth: => Either[FAIL12, SUC12],
      thirteenth: => Either[FAIL13, SUC13]
  ): Either[
    (
        FAIL1,
        FAIL2,
        FAIL3,
        FAIL4,
        FAIL5,
        FAIL6,
        FAIL7,
        FAIL8,
        FAIL9,
        FAIL10,
        FAIL11,
        FAIL12,
        FAIL13
    ),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13
  ] = {
    firstSuccess[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5 | FAIL6 | FAIL7 | FAIL8 | FAIL9 | FAIL10 | FAIL11 | FAIL12 | FAIL13,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13
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
        () => tenth,
        () => eleventh,
        () => twelfth,
        () => thirteenth
      )
    ).left.map {
      case Seq(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13) =>
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
          f10.asInstanceOf[FAIL10],
          f11.asInstanceOf[FAIL11],
          f12.asInstanceOf[FAIL12],
          f13.asInstanceOf[FAIL13]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [13]"
        )
    }
  }

  def firstSuccess[
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
      FAIL10,
      SUC11,
      FAIL11,
      SUC12,
      FAIL12,
      SUC13,
      FAIL13,
      SUC14,
      FAIL14
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
      tenth: => Either[FAIL10, SUC10],
      eleventh: => Either[FAIL11, SUC11],
      twelfth: => Either[FAIL12, SUC12],
      thirteenth: => Either[FAIL13, SUC13],
      fourteenth: => Either[FAIL14, SUC14]
  ): Either[
    (
        FAIL1,
        FAIL2,
        FAIL3,
        FAIL4,
        FAIL5,
        FAIL6,
        FAIL7,
        FAIL8,
        FAIL9,
        FAIL10,
        FAIL11,
        FAIL12,
        FAIL13,
        FAIL14
    ),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14
  ] = {
    firstSuccess[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5 | FAIL6 | FAIL7 | FAIL8 | FAIL9 | FAIL10 | FAIL11 | FAIL12 | FAIL13 | FAIL14,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14
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
        () => tenth,
        () => eleventh,
        () => twelfth,
        () => thirteenth,
        () => fourteenth
      )
    ).left.map {
      case Seq(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14) =>
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
          f10.asInstanceOf[FAIL10],
          f11.asInstanceOf[FAIL11],
          f12.asInstanceOf[FAIL12],
          f13.asInstanceOf[FAIL13],
          f14.asInstanceOf[FAIL14]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [14]"
        )
    }
  }

  def firstSuccess[
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
      FAIL10,
      SUC11,
      FAIL11,
      SUC12,
      FAIL12,
      SUC13,
      FAIL13,
      SUC14,
      FAIL14,
      SUC15,
      FAIL15
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
      tenth: => Either[FAIL10, SUC10],
      eleventh: => Either[FAIL11, SUC11],
      twelfth: => Either[FAIL12, SUC12],
      thirteenth: => Either[FAIL13, SUC13],
      fourteenth: => Either[FAIL14, SUC14],
      fifteenth: => Either[FAIL15, SUC15]
  ): Either[
    (
        FAIL1,
        FAIL2,
        FAIL3,
        FAIL4,
        FAIL5,
        FAIL6,
        FAIL7,
        FAIL8,
        FAIL9,
        FAIL10,
        FAIL11,
        FAIL12,
        FAIL13,
        FAIL14,
        FAIL15
    ),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14 | SUC15
  ] = {
    firstSuccess[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5 | FAIL6 | FAIL7 | FAIL8 | FAIL9 | FAIL10 | FAIL11 | FAIL12 | FAIL13 | FAIL14 | FAIL15,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14 | SUC15
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
        () => tenth,
        () => eleventh,
        () => twelfth,
        () => thirteenth,
        () => fourteenth,
        () => fifteenth
      )
    ).left.map {
      case Seq(
            f1,
            f2,
            f3,
            f4,
            f5,
            f6,
            f7,
            f8,
            f9,
            f10,
            f11,
            f12,
            f13,
            f14,
            f15
          ) =>
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
          f10.asInstanceOf[FAIL10],
          f11.asInstanceOf[FAIL11],
          f12.asInstanceOf[FAIL12],
          f13.asInstanceOf[FAIL13],
          f14.asInstanceOf[FAIL14],
          f15.asInstanceOf[FAIL15]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [15]"
        )
    }
  }

  def firstSuccess[
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
      FAIL10,
      SUC11,
      FAIL11,
      SUC12,
      FAIL12,
      SUC13,
      FAIL13,
      SUC14,
      FAIL14,
      SUC15,
      FAIL15,
      SUC16,
      FAIL16
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
      tenth: => Either[FAIL10, SUC10],
      eleventh: => Either[FAIL11, SUC11],
      twelfth: => Either[FAIL12, SUC12],
      thirteenth: => Either[FAIL13, SUC13],
      fourteenth: => Either[FAIL14, SUC14],
      fifteenth: => Either[FAIL15, SUC15],
      sixteenth: => Either[FAIL16, SUC16]
  ): Either[
    (
        FAIL1,
        FAIL2,
        FAIL3,
        FAIL4,
        FAIL5,
        FAIL6,
        FAIL7,
        FAIL8,
        FAIL9,
        FAIL10,
        FAIL11,
        FAIL12,
        FAIL13,
        FAIL14,
        FAIL15,
        FAIL16
    ),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14 | SUC15 | SUC16
  ] = {
    firstSuccess[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5 | FAIL6 | FAIL7 | FAIL8 | FAIL9 | FAIL10 | FAIL11 | FAIL12 | FAIL13 | FAIL14 | FAIL15 | FAIL16,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14 | SUC15 | SUC16
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
        () => tenth,
        () => eleventh,
        () => twelfth,
        () => thirteenth,
        () => fourteenth,
        () => fifteenth,
        () => sixteenth
      )
    ).left.map {
      case Seq(
            f1,
            f2,
            f3,
            f4,
            f5,
            f6,
            f7,
            f8,
            f9,
            f10,
            f11,
            f12,
            f13,
            f14,
            f15,
            f16
          ) =>
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
          f10.asInstanceOf[FAIL10],
          f11.asInstanceOf[FAIL11],
          f12.asInstanceOf[FAIL12],
          f13.asInstanceOf[FAIL13],
          f14.asInstanceOf[FAIL14],
          f15.asInstanceOf[FAIL15],
          f16.asInstanceOf[FAIL16]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [16]"
        )
    }
  }

  def firstSuccess[
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
      FAIL10,
      SUC11,
      FAIL11,
      SUC12,
      FAIL12,
      SUC13,
      FAIL13,
      SUC14,
      FAIL14,
      SUC15,
      FAIL15,
      SUC16,
      FAIL16,
      SUC17,
      FAIL17
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
      tenth: => Either[FAIL10, SUC10],
      eleventh: => Either[FAIL11, SUC11],
      twelfth: => Either[FAIL12, SUC12],
      thirteenth: => Either[FAIL13, SUC13],
      fourteenth: => Either[FAIL14, SUC14],
      fifteenth: => Either[FAIL15, SUC15],
      sixteenth: => Either[FAIL16, SUC16],
      seventeenth: => Either[FAIL17, SUC17]
  ): Either[
    (
        FAIL1,
        FAIL2,
        FAIL3,
        FAIL4,
        FAIL5,
        FAIL6,
        FAIL7,
        FAIL8,
        FAIL9,
        FAIL10,
        FAIL11,
        FAIL12,
        FAIL13,
        FAIL14,
        FAIL15,
        FAIL16,
        FAIL17
    ),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14 | SUC15 | SUC16 | SUC17
  ] = {
    firstSuccess[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5 | FAIL6 | FAIL7 | FAIL8 | FAIL9 | FAIL10 | FAIL11 | FAIL12 | FAIL13 | FAIL14 | FAIL15 | FAIL16 | FAIL17,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14 | SUC15 | SUC16 | SUC17
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
        () => tenth,
        () => eleventh,
        () => twelfth,
        () => thirteenth,
        () => fourteenth,
        () => fifteenth,
        () => sixteenth,
        () => seventeenth
      )
    ).left.map {
      case Seq(
            f1,
            f2,
            f3,
            f4,
            f5,
            f6,
            f7,
            f8,
            f9,
            f10,
            f11,
            f12,
            f13,
            f14,
            f15,
            f16,
            f17
          ) =>
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
          f10.asInstanceOf[FAIL10],
          f11.asInstanceOf[FAIL11],
          f12.asInstanceOf[FAIL12],
          f13.asInstanceOf[FAIL13],
          f14.asInstanceOf[FAIL14],
          f15.asInstanceOf[FAIL15],
          f16.asInstanceOf[FAIL16],
          f17.asInstanceOf[FAIL17]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [17]"
        )
    }
  }

  def firstSuccess[
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
      FAIL10,
      SUC11,
      FAIL11,
      SUC12,
      FAIL12,
      SUC13,
      FAIL13,
      SUC14,
      FAIL14,
      SUC15,
      FAIL15,
      SUC16,
      FAIL16,
      SUC17,
      FAIL17,
      SUC18,
      FAIL18
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
      tenth: => Either[FAIL10, SUC10],
      eleventh: => Either[FAIL11, SUC11],
      twelfth: => Either[FAIL12, SUC12],
      thirteenth: => Either[FAIL13, SUC13],
      fourteenth: => Either[FAIL14, SUC14],
      fifteenth: => Either[FAIL15, SUC15],
      sixteenth: => Either[FAIL16, SUC16],
      seventeenth: => Either[FAIL17, SUC17],
      eighteenth: => Either[FAIL18, SUC18]
  ): Either[
    (
        FAIL1,
        FAIL2,
        FAIL3,
        FAIL4,
        FAIL5,
        FAIL6,
        FAIL7,
        FAIL8,
        FAIL9,
        FAIL10,
        FAIL11,
        FAIL12,
        FAIL13,
        FAIL14,
        FAIL15,
        FAIL16,
        FAIL17,
        FAIL18
    ),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14 | SUC15 | SUC16 | SUC17 | SUC18
  ] = {
    firstSuccess[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5 | FAIL6 | FAIL7 | FAIL8 | FAIL9 | FAIL10 | FAIL11 | FAIL12 | FAIL13 | FAIL14 | FAIL15 | FAIL16 | FAIL17 | FAIL18,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14 | SUC15 | SUC16 | SUC17 | SUC18
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
        () => tenth,
        () => eleventh,
        () => twelfth,
        () => thirteenth,
        () => fourteenth,
        () => fifteenth,
        () => sixteenth,
        () => seventeenth,
        () => eighteenth
      )
    ).left.map {
      case Seq(
            f1,
            f2,
            f3,
            f4,
            f5,
            f6,
            f7,
            f8,
            f9,
            f10,
            f11,
            f12,
            f13,
            f14,
            f15,
            f16,
            f17,
            f18
          ) =>
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
          f10.asInstanceOf[FAIL10],
          f11.asInstanceOf[FAIL11],
          f12.asInstanceOf[FAIL12],
          f13.asInstanceOf[FAIL13],
          f14.asInstanceOf[FAIL14],
          f15.asInstanceOf[FAIL15],
          f16.asInstanceOf[FAIL16],
          f17.asInstanceOf[FAIL17],
          f18.asInstanceOf[FAIL18]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [18]"
        )
    }
  }

  def firstSuccess[
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
      FAIL10,
      SUC11,
      FAIL11,
      SUC12,
      FAIL12,
      SUC13,
      FAIL13,
      SUC14,
      FAIL14,
      SUC15,
      FAIL15,
      SUC16,
      FAIL16,
      SUC17,
      FAIL17,
      SUC18,
      FAIL18,
      SUC19,
      FAIL19
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
      tenth: => Either[FAIL10, SUC10],
      eleventh: => Either[FAIL11, SUC11],
      twelfth: => Either[FAIL12, SUC12],
      thirteenth: => Either[FAIL13, SUC13],
      fourteenth: => Either[FAIL14, SUC14],
      fifteenth: => Either[FAIL15, SUC15],
      sixteenth: => Either[FAIL16, SUC16],
      seventeenth: => Either[FAIL17, SUC17],
      eighteenth: => Either[FAIL18, SUC18],
      nineteenth: => Either[FAIL19, SUC19]
  ): Either[
    (
        FAIL1,
        FAIL2,
        FAIL3,
        FAIL4,
        FAIL5,
        FAIL6,
        FAIL7,
        FAIL8,
        FAIL9,
        FAIL10,
        FAIL11,
        FAIL12,
        FAIL13,
        FAIL14,
        FAIL15,
        FAIL16,
        FAIL17,
        FAIL18,
        FAIL19
    ),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14 | SUC15 | SUC16 | SUC17 | SUC18 | SUC19
  ] = {
    firstSuccess[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5 | FAIL6 | FAIL7 | FAIL8 | FAIL9 | FAIL10 | FAIL11 | FAIL12 | FAIL13 | FAIL14 | FAIL15 | FAIL16 | FAIL17 | FAIL18 | FAIL19,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14 | SUC15 | SUC16 | SUC17 | SUC18 | SUC19
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
        () => tenth,
        () => eleventh,
        () => twelfth,
        () => thirteenth,
        () => fourteenth,
        () => fifteenth,
        () => sixteenth,
        () => seventeenth,
        () => eighteenth,
        () => nineteenth
      )
    ).left.map {
      case Seq(
            f1,
            f2,
            f3,
            f4,
            f5,
            f6,
            f7,
            f8,
            f9,
            f10,
            f11,
            f12,
            f13,
            f14,
            f15,
            f16,
            f17,
            f18,
            f19
          ) =>
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
          f10.asInstanceOf[FAIL10],
          f11.asInstanceOf[FAIL11],
          f12.asInstanceOf[FAIL12],
          f13.asInstanceOf[FAIL13],
          f14.asInstanceOf[FAIL14],
          f15.asInstanceOf[FAIL15],
          f16.asInstanceOf[FAIL16],
          f17.asInstanceOf[FAIL17],
          f18.asInstanceOf[FAIL18],
          f19.asInstanceOf[FAIL19]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [19]"
        )
    }
  }

  def firstSuccess[
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
      FAIL10,
      SUC11,
      FAIL11,
      SUC12,
      FAIL12,
      SUC13,
      FAIL13,
      SUC14,
      FAIL14,
      SUC15,
      FAIL15,
      SUC16,
      FAIL16,
      SUC17,
      FAIL17,
      SUC18,
      FAIL18,
      SUC19,
      FAIL19,
      SUC20,
      FAIL20
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
      tenth: => Either[FAIL10, SUC10],
      eleventh: => Either[FAIL11, SUC11],
      twelfth: => Either[FAIL12, SUC12],
      thirteenth: => Either[FAIL13, SUC13],
      fourteenth: => Either[FAIL14, SUC14],
      fifteenth: => Either[FAIL15, SUC15],
      sixteenth: => Either[FAIL16, SUC16],
      seventeenth: => Either[FAIL17, SUC17],
      eighteenth: => Either[FAIL18, SUC18],
      nineteenth: => Either[FAIL19, SUC19],
      twentieth: => Either[FAIL20, SUC20]
  ): Either[
    (
        FAIL1,
        FAIL2,
        FAIL3,
        FAIL4,
        FAIL5,
        FAIL6,
        FAIL7,
        FAIL8,
        FAIL9,
        FAIL10,
        FAIL11,
        FAIL12,
        FAIL13,
        FAIL14,
        FAIL15,
        FAIL16,
        FAIL17,
        FAIL18,
        FAIL19,
        FAIL20
    ),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14 | SUC15 | SUC16 | SUC17 | SUC18 | SUC19 | SUC20
  ] = {
    firstSuccess[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5 | FAIL6 | FAIL7 | FAIL8 | FAIL9 | FAIL10 | FAIL11 | FAIL12 | FAIL13 | FAIL14 | FAIL15 | FAIL16 | FAIL17 | FAIL18 | FAIL19 | FAIL20,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14 | SUC15 | SUC16 | SUC17 | SUC18 | SUC19 | SUC20
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
        () => tenth,
        () => eleventh,
        () => twelfth,
        () => thirteenth,
        () => fourteenth,
        () => fifteenth,
        () => sixteenth,
        () => seventeenth,
        () => eighteenth,
        () => nineteenth,
        () => twentieth
      )
    ).left.map {
      case Seq(
            f1,
            f2,
            f3,
            f4,
            f5,
            f6,
            f7,
            f8,
            f9,
            f10,
            f11,
            f12,
            f13,
            f14,
            f15,
            f16,
            f17,
            f18,
            f19,
            f20
          ) =>
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
          f10.asInstanceOf[FAIL10],
          f11.asInstanceOf[FAIL11],
          f12.asInstanceOf[FAIL12],
          f13.asInstanceOf[FAIL13],
          f14.asInstanceOf[FAIL14],
          f15.asInstanceOf[FAIL15],
          f16.asInstanceOf[FAIL16],
          f17.asInstanceOf[FAIL17],
          f18.asInstanceOf[FAIL18],
          f19.asInstanceOf[FAIL19],
          f20.asInstanceOf[FAIL20]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [20]"
        )
    }
  }

  def firstSuccess[
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
      FAIL10,
      SUC11,
      FAIL11,
      SUC12,
      FAIL12,
      SUC13,
      FAIL13,
      SUC14,
      FAIL14,
      SUC15,
      FAIL15,
      SUC16,
      FAIL16,
      SUC17,
      FAIL17,
      SUC18,
      FAIL18,
      SUC19,
      FAIL19,
      SUC20,
      FAIL20,
      SUC21,
      FAIL21
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
      tenth: => Either[FAIL10, SUC10],
      eleventh: => Either[FAIL11, SUC11],
      twelfth: => Either[FAIL12, SUC12],
      thirteenth: => Either[FAIL13, SUC13],
      fourteenth: => Either[FAIL14, SUC14],
      fifteenth: => Either[FAIL15, SUC15],
      sixteenth: => Either[FAIL16, SUC16],
      seventeenth: => Either[FAIL17, SUC17],
      eighteenth: => Either[FAIL18, SUC18],
      nineteenth: => Either[FAIL19, SUC19],
      twentieth: => Either[FAIL20, SUC20],
      twentyFirst: => Either[FAIL21, SUC21]
  ): Either[
    (
        FAIL1,
        FAIL2,
        FAIL3,
        FAIL4,
        FAIL5,
        FAIL6,
        FAIL7,
        FAIL8,
        FAIL9,
        FAIL10,
        FAIL11,
        FAIL12,
        FAIL13,
        FAIL14,
        FAIL15,
        FAIL16,
        FAIL17,
        FAIL18,
        FAIL19,
        FAIL20,
        FAIL21
    ),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14 | SUC15 | SUC16 | SUC17 | SUC18 | SUC19 | SUC20 | SUC21
  ] = {
    firstSuccess[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5 | FAIL6 | FAIL7 | FAIL8 | FAIL9 | FAIL10 | FAIL11 | FAIL12 | FAIL13 | FAIL14 | FAIL15 | FAIL16 | FAIL17 | FAIL18 | FAIL19 | FAIL20 | FAIL21,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14 | SUC15 | SUC16 | SUC17 | SUC18 | SUC19 | SUC20 | SUC21
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
        () => tenth,
        () => eleventh,
        () => twelfth,
        () => thirteenth,
        () => fourteenth,
        () => fifteenth,
        () => sixteenth,
        () => seventeenth,
        () => eighteenth,
        () => nineteenth,
        () => twentieth,
        () => twentyFirst
      )
    ).left.map {
      case Seq(
            f1,
            f2,
            f3,
            f4,
            f5,
            f6,
            f7,
            f8,
            f9,
            f10,
            f11,
            f12,
            f13,
            f14,
            f15,
            f16,
            f17,
            f18,
            f19,
            f20,
            f21
          ) =>
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
          f10.asInstanceOf[FAIL10],
          f11.asInstanceOf[FAIL11],
          f12.asInstanceOf[FAIL12],
          f13.asInstanceOf[FAIL13],
          f14.asInstanceOf[FAIL14],
          f15.asInstanceOf[FAIL15],
          f16.asInstanceOf[FAIL16],
          f17.asInstanceOf[FAIL17],
          f18.asInstanceOf[FAIL18],
          f19.asInstanceOf[FAIL19],
          f20.asInstanceOf[FAIL20],
          f21.asInstanceOf[FAIL21]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [21]"
        )
    }
  }

  def firstSuccess[
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
      FAIL10,
      SUC11,
      FAIL11,
      SUC12,
      FAIL12,
      SUC13,
      FAIL13,
      SUC14,
      FAIL14,
      SUC15,
      FAIL15,
      SUC16,
      FAIL16,
      SUC17,
      FAIL17,
      SUC18,
      FAIL18,
      SUC19,
      FAIL19,
      SUC20,
      FAIL20,
      SUC21,
      FAIL21,
      SUC22,
      FAIL22
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
      tenth: => Either[FAIL10, SUC10],
      eleventh: => Either[FAIL11, SUC11],
      twelfth: => Either[FAIL12, SUC12],
      thirteenth: => Either[FAIL13, SUC13],
      fourteenth: => Either[FAIL14, SUC14],
      fifteenth: => Either[FAIL15, SUC15],
      sixteenth: => Either[FAIL16, SUC16],
      seventeenth: => Either[FAIL17, SUC17],
      eighteenth: => Either[FAIL18, SUC18],
      nineteenth: => Either[FAIL19, SUC19],
      twentieth: => Either[FAIL20, SUC20],
      twentyFirst: => Either[FAIL21, SUC21],
      twentySecond: => Either[FAIL22, SUC22]
  ): Either[
    (
        FAIL1,
        FAIL2,
        FAIL3,
        FAIL4,
        FAIL5,
        FAIL6,
        FAIL7,
        FAIL8,
        FAIL9,
        FAIL10,
        FAIL11,
        FAIL12,
        FAIL13,
        FAIL14,
        FAIL15,
        FAIL16,
        FAIL17,
        FAIL18,
        FAIL19,
        FAIL20,
        FAIL21,
        FAIL22
    ),
    SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14 | SUC15 | SUC16 | SUC17 | SUC18 | SUC19 | SUC20 | SUC21 | SUC22
  ] = {
    firstSuccess[
      FAIL1 | FAIL2 | FAIL3 | FAIL4 | FAIL5 | FAIL6 | FAIL7 | FAIL8 | FAIL9 | FAIL10 | FAIL11 | FAIL12 | FAIL13 | FAIL14 | FAIL15 | FAIL16 | FAIL17 | FAIL18 | FAIL19 | FAIL20 | FAIL21 | FAIL22,
      SUC1 | SUC2 | SUC3 | SUC4 | SUC5 | SUC6 | SUC7 | SUC8 | SUC9 | SUC10 | SUC11 | SUC12 | SUC13 | SUC14 | SUC15 | SUC16 | SUC17 | SUC18 | SUC19 | SUC20 | SUC21 | SUC22
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
        () => tenth,
        () => eleventh,
        () => twelfth,
        () => thirteenth,
        () => fourteenth,
        () => fifteenth,
        () => sixteenth,
        () => seventeenth,
        () => eighteenth,
        () => nineteenth,
        () => twentieth,
        () => twentyFirst,
        () => twentySecond
      )
    ).left.map {
      case Seq(
            f1,
            f2,
            f3,
            f4,
            f5,
            f6,
            f7,
            f8,
            f9,
            f10,
            f11,
            f12,
            f13,
            f14,
            f15,
            f16,
            f17,
            f18,
            f19,
            f20,
            f21,
            f22
          ) =>
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
          f10.asInstanceOf[FAIL10],
          f11.asInstanceOf[FAIL11],
          f12.asInstanceOf[FAIL12],
          f13.asInstanceOf[FAIL13],
          f14.asInstanceOf[FAIL14],
          f15.asInstanceOf[FAIL15],
          f16.asInstanceOf[FAIL16],
          f17.asInstanceOf[FAIL17],
          f18.asInstanceOf[FAIL18],
          f19.asInstanceOf[FAIL19],
          f20.asInstanceOf[FAIL20],
          f21.asInstanceOf[FAIL21],
          f22.asInstanceOf[FAIL22]
        )
      case seq =>
        throw new IllegalStateException(
          s"Unexpected number of failures: [${seq.length}], expected [22]"
        )
    }
  }

}
