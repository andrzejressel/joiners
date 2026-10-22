package pl.andrzejressel.joiners4s

import scala.annotation.tailrec
import java.util.concurrent.atomic.AtomicReference

/** Extension of [[AtomicReference]] that allows returning arbitrary additional
  * value from the update function. Used as part of the StateMachine in
  * [[pl.andrzejressel.joiners4s.joiners.FirstSuccessfulEitherJoiner]] where the
  * additional value is used to indicate whether the scope should continue or
  * cancel the remaining tasks.
  *
  * @param ref
  *   The underlying [[AtomicReference]] that holds the state.
  * @tparam S
  *   The type of the state.
  */
final private class AtomicState[S](private val ref: AtomicReference[S]) {

  /** Creates a new [[AtomicState]] with the given initial state.
    *
    * @param initial
    *   The initial state.
    */
  def this(initial: S) = this(new AtomicReference(initial))

  /** Atomically updates the state using the provided function and returns an
    * additional value. The function takes the current state and returns a tuple
    * containing the new state and an additional value. If the state has changed
    * since the function was called, it retries until it succeeds
    *
    * @param fn
    *   The function that takes the current state and returns a tuple of the new
    *   state and an additional value.
    * @tparam R
    *   The type of the additional value returned by the function.
    * @return
    *   The additional value returned by the function.
    */
  @tailrec
  def update[R](fn: S => (S, R)): R = {
    val current = ref.get
    val (s, r) = fn(current)
    if ref.compareAndSet(current, s) then r
    else update(fn)
  }

  /** @return
    *   The current state.
    */
  def get: S = ref.get

}
