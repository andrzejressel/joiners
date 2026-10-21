package pl.andrzejressel.strcon4s

import scala.collection.mutable

/** Thread safe wrapper around mutable Seq that allow for inserting into
  * arbitrary (potentially non-existing) indices. If an index is out of bounds,
  * the sequence will be expanded with default values (nulls) to accommodate the
  * new index. It is used for
  * [[pl.andrzejressel.strcon4s.joiners.FirstSuccessfulEitherJoiner]] where the
  * order of the results is important and I don't want to require user to
  * provide number of elements in advance.
  *
  * @tparam A
  *   The type of elements in the buffer.
  * @param innerSeq
  *   The underlying mutable ArrayBuffer that holds the elements.
  */
class AutoExpandableSeq[A](private val innerSeq: mutable.ArrayBuffer[A]) {

  /** Creates a new [[AutoExpandableSeq]] with an empty underlying mutable
    * ArrayBuffer.
    */
  def this() = this(mutable.ArrayBuffer.empty[A])

  /** Returns the element at the specified index. If the index is out of bounds,
    * it will throw an IndexOutOfBoundsException.
    * @param index
    *   The index of the element to retrieve.
    * @return
    *   The element at the specified index
    * @throws IndexOutOfBoundsException
    *   if the index is out of bounds.
    */
  def apply(index: Int): A = synchronized {
    innerSeq(index)
  }

  /** Updates the element at the specified index. If the index is out of bounds,
    * the sequence will be expanded with default values (nulls) to accommodate
    * the new index.
    * @param index
    *   The index of the element to update.
    * @param value
    *   The new value to set at the specified index.
    */
  def update(index: Int, value: A): Unit = synchronized {
    innerSeq.padToInPlace(index + 1, null.asInstanceOf[A])
    innerSeq(index) = value
  }

  /** @return
    *   Immutable close of the underlying mutable ArrayBuffer
    */
  def toSeq: Seq[A] = synchronized {
    innerSeq.toSeq
  }

}
