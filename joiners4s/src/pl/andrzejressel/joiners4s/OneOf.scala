package pl.andrzejressel.joiners4s

enum OneOf2[+A, +B]:
  case First(value: A) extends OneOf2[A, Nothing]
  case Second(value: B) extends OneOf2[Nothing, B]

  def fold[R](f1: A => R, f2: B => R): R = this match
    case First(v) => f1(v)
    case Second(v) => f2(v)

  def toUnion: A | B = this match
    case First(v) => v
    case Second(v) => v

object OneOf2 {
  def forIndex(index: Int, value: Any): OneOf2[Any, Any] = index match
    case 0 => OneOf2.First(value)
    case 1 => OneOf2.Second(value)
    case _ => throw new IllegalArgumentException(s"Index $index is out of bounds for OneOf2")
}

enum OneOf3[+A, +B, +C]:
  case First(value: A) extends OneOf3[A, Nothing, Nothing]
  case Second(value: B) extends OneOf3[Nothing, B, Nothing]
  case Third(value: C) extends OneOf3[Nothing, Nothing, C]

  def fold[R](f1: A => R, f2: B => R, f3: C => R): R = this match
    case First(v) => f1(v)
    case Second(v) => f2(v)
    case Third(v) => f3(v)

  def toUnion: A | B | C = this match
    case First(v) => v
    case Second(v) => v
    case Third(v) => v


enum OneOf4[+A, +B, +C, +D]:
  case First(value: A) extends OneOf4[A, Nothing, Nothing, Nothing]
  case Second(value: B) extends OneOf4[Nothing, B, Nothing, Nothing]
  case Third(value: C) extends OneOf4[Nothing, Nothing, C, Nothing]
  case Fourth(value: D) extends OneOf4[Nothing, Nothing, Nothing, D]

  def fold[R](f1: A => R, f2: B => R, f3: C => R, f4: D => R): R = this match
    case First(v) => f1(v)
    case Second(v) => f2(v)
    case Third(v) => f3(v)
    case Fourth(v) => f4(v)

  def toUnion: A | B | C | D = this match
    case First(v) => v
    case Second(v) => v
    case Third(v) => v
    case Fourth(v) => v


enum OneOf5[+A, +B, +C, +D, +E]:
  case First(value: A) extends OneOf5[A, Nothing, Nothing, Nothing, Nothing]
  case Second(value: B) extends OneOf5[Nothing, B, Nothing, Nothing, Nothing]
  case Third(value: C) extends OneOf5[Nothing, Nothing, C, Nothing, Nothing]
  case Fourth(value: D) extends OneOf5[Nothing, Nothing, Nothing, D, Nothing]
  case Fifth(value: E) extends OneOf5[Nothing, Nothing, Nothing, Nothing, E]

  def fold[R](f1: A => R, f2: B => R, f3: C => R, f4: D => R, f5: E => R): R = this match
    case First(v) => f1(v)
    case Second(v) => f2(v)
    case Third(v) => f3(v)
    case Fourth(v) => f4(v)
    case Fifth(v) => f5(v)

  def toUnion: A | B | C | D | E = this match
    case First(v) => v
    case Second(v) => v
    case Third(v) => v
    case Fourth(v) => v
    case Fifth(v) => v


enum OneOf6[+A, +B, +C, +D, +E, +F]:
  case First(value: A) extends OneOf6[A, Nothing, Nothing, Nothing, Nothing, Nothing]
  case Second(value: B) extends OneOf6[Nothing, B, Nothing, Nothing, Nothing, Nothing]
  case Third(value: C) extends OneOf6[Nothing, Nothing, C, Nothing, Nothing, Nothing]
  case Fourth(value: D) extends OneOf6[Nothing, Nothing, Nothing, D, Nothing, Nothing]
  case Fifth(value: E) extends OneOf6[Nothing, Nothing, Nothing, Nothing, E, Nothing]
  case Sixth(value: F) extends OneOf6[Nothing, Nothing, Nothing, Nothing, Nothing, F]

  def fold[R](f1: A => R, f2: B => R, f3: C => R, f4: D => R, f5: E => R, f6: F => R): R = this match
    case First(v) => f1(v)
    case Second(v) => f2(v)
    case Third(v) => f3(v)
    case Fourth(v) => f4(v)
    case Fifth(v) => f5(v)
    case Sixth(v) => f6(v)

  def toUnion: A | B | C | D | E | F = this match
    case First(v) => v
    case Second(v) => v
    case Third(v) => v
    case Fourth(v) => v
    case Fifth(v) => v
    case Sixth(v) => v


enum OneOf7[+A, +B, +C, +D, +E, +F, +G]:
  case First(value: A) extends OneOf7[A, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing]
  case Second(value: B) extends OneOf7[Nothing, B, Nothing, Nothing, Nothing, Nothing, Nothing]
  case Third(value: C) extends OneOf7[Nothing, Nothing, C, Nothing, Nothing, Nothing, Nothing]
  case Fourth(value: D) extends OneOf7[Nothing, Nothing, Nothing, D, Nothing, Nothing, Nothing]
  case Fifth(value: E) extends OneOf7[Nothing, Nothing, Nothing, Nothing, E, Nothing, Nothing]
  case Sixth(value: F) extends OneOf7[Nothing, Nothing, Nothing, Nothing, Nothing, F, Nothing]
  case Seventh(value: G) extends OneOf7[Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, G]

  def fold[R](f1: A => R, f2: B => R, f3: C => R, f4: D => R, f5: E => R, f6: F => R, f7: G => R): R = this match
    case First(v) => f1(v)
    case Second(v) => f2(v)
    case Third(v) => f3(v)
    case Fourth(v) => f4(v)
    case Fifth(v) => f5(v)
    case Sixth(v) => f6(v)
    case Seventh(v) => f7(v)

  def toUnion: A | B | C | D | E | F | G = this match
    case First(v) => v
    case Second(v) => v
    case Third(v) => v
    case Fourth(v) => v
    case Fifth(v) => v
    case Sixth(v) => v
    case Seventh(v) => v


enum OneOf8[+A, +B, +C, +D, +E, +F, +G, +H]:
  case First(value: A) extends OneOf8[A, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing]
  case Second(value: B) extends OneOf8[Nothing, B, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing]
  case Third(value: C) extends OneOf8[Nothing, Nothing, C, Nothing, Nothing, Nothing, Nothing, Nothing]
  case Fourth(value: D) extends OneOf8[Nothing, Nothing, Nothing, D, Nothing, Nothing, Nothing, Nothing]
  case Fifth(value: E) extends OneOf8[Nothing, Nothing, Nothing, Nothing, E, Nothing, Nothing, Nothing]
  case Sixth(value: F) extends OneOf8[Nothing, Nothing, Nothing, Nothing, Nothing, F, Nothing, Nothing]
  case Seventh(value: G) extends OneOf8[Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, G, Nothing]
  case Eighth(value: H) extends OneOf8[Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, H]

  def fold[R](f1: A => R, f2: B => R, f3: C => R, f4: D => R, f5: E => R, f6: F => R, f7: G => R, f8: H => R): R = this match
    case First(v) => f1(v)
    case Second(v) => f2(v)
    case Third(v) => f3(v)
    case Fourth(v) => f4(v)
    case Fifth(v) => f5(v)
    case Sixth(v) => f6(v)
    case Seventh(v) => f7(v)
    case Eighth(v) => f8(v)

  def toUnion: A | B | C | D | E | F | G | H = this match
    case First(v) => v
    case Second(v) => v
    case Third(v) => v
    case Fourth(v) => v
    case Fifth(v) => v
    case Sixth(v) => v
    case Seventh(v) => v
    case Eighth(v) => v


enum OneOf9[+A, +B, +C, +D, +E, +F, +G, +H, +I]:
  case First(value: A) extends OneOf9[A, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing]
  case Second(value: B) extends OneOf9[Nothing, B, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing]
  case Third(value: C) extends OneOf9[Nothing, Nothing, C, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing]
  case Fourth(value: D) extends OneOf9[Nothing, Nothing, Nothing, D, Nothing, Nothing, Nothing, Nothing, Nothing]
  case Fifth(value: E) extends OneOf9[Nothing, Nothing, Nothing, Nothing, E, Nothing, Nothing, Nothing, Nothing]
  case Sixth(value: F) extends OneOf9[Nothing, Nothing, Nothing, Nothing, Nothing, F, Nothing, Nothing, Nothing]
  case Seventh(value: G) extends OneOf9[Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, G, Nothing, Nothing]
  case Eighth(value: H) extends OneOf9[Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, H, Nothing]
  case Ninth(value: I) extends OneOf9[Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, I]

  def fold[R](f1: A => R, f2: B => R, f3: C => R, f4: D => R, f5: E => R, f6: F => R, f7: G => R, f8: H => R, f9: I => R): R = this match
    case First(v) => f1(v)
    case Second(v) => f2(v)
    case Third(v) => f3(v)
    case Fourth(v) => f4(v)
    case Fifth(v) => f5(v)
    case Sixth(v) => f6(v)
    case Seventh(v) => f7(v)
    case Eighth(v) => f8(v)
    case Ninth(v) => f9(v)

  def toUnion: A | B | C | D | E | F | G | H | I = this match
    case First(v) => v
    case Second(v) => v
    case Third(v) => v
    case Fourth(v) => v
    case Fifth(v) => v
    case Sixth(v) => v
    case Seventh(v) => v
    case Eighth(v) => v
    case Ninth(v) => v


enum OneOf10[+A, +B, +C, +D, +E, +F, +G, +H, +I, +J]:
  case First(value: A) extends OneOf10[A, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing]
  case Second(value: B) extends OneOf10[Nothing, B, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing]
  case Third(value: C) extends OneOf10[Nothing, Nothing, C, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing]
  case Fourth(value: D) extends OneOf10[Nothing, Nothing, Nothing, D, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing]
  case Fifth(value: E) extends OneOf10[Nothing, Nothing, Nothing, Nothing, E, Nothing, Nothing, Nothing, Nothing, Nothing]
  case Sixth(value: F) extends OneOf10[Nothing, Nothing, Nothing, Nothing, Nothing, F, Nothing, Nothing, Nothing, Nothing]
  case Seventh(value: G) extends OneOf10[Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, G, Nothing, Nothing, Nothing]
  case Eighth(value: H) extends OneOf10[Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, H, Nothing, Nothing]
  case Ninth(value: I) extends OneOf10[Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, I, Nothing]
  case Tenth(value: J) extends OneOf10[Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, Nothing, J]

  def fold[R](f1: A => R, f2: B => R, f3: C => R, f4: D => R, f5: E => R, f6: F => R, f7: G => R, f8: H => R, f9: I => R, f10: J => R): R = this match
    case First(v) => f1(v)
    case Second(v) => f2(v)
    case Third(v) => f3(v)
    case Fourth(v) => f4(v)
    case Fifth(v) => f5(v)
    case Sixth(v) => f6(v)
    case Seventh(v) => f7(v)
    case Eighth(v) => f8(v)
    case Ninth(v) => f9(v)
    case Tenth(v) => f10(v)

  def toUnion: A | B | C | D | E | F | G | H | I | J = this match
    case First(v) => v
    case Second(v) => v
    case Third(v) => v
    case Fourth(v) => v
    case Fifth(v) => v
    case Sixth(v) => v
    case Seventh(v) => v
    case Eighth(v) => v
    case Ninth(v) => v
    case Tenth(v) => v