package pl.andrzejressel.joiners.scala

class AtomicStateSuite extends munit.FunSuite {

  test("update should return the correct result") {
    val state = AtomicState(5)
    val result = state.update { current =>
      (current + 1, current * 2)
    }
    assertEquals(result, 10)
    assertEquals(state.get, 6)
  }

  test("ABC") {

    type TupleToUnion[T <: Tuple] = T match {
      case EmptyTuple => Nothing
      case h *: t     => h | TupleToUnion[t]
    }

    summon[TupleToUnion[(Int, String, Boolean)] =:= (Int | String | Boolean)]

  }

}
