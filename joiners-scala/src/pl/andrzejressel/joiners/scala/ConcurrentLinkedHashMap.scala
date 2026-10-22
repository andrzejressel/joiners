package pl.andrzejressel.joiners.scala

import scala.collection.mutable

/** [[scala.collection.mutable.LinkedHashMap]] that can be used in a multithreaded context.
  */
private class ConcurrentLinkedHashMap[K, V] private (
    private val map: mutable.LinkedHashMap[K, V]
) {

  def this() = this(mutable.LinkedHashMap.empty[K, V])

  def put(key: K, value: V): Unit = synchronized {
    map.put(key, value)
  }

  def get(key: K): Option[V] = synchronized {
    map.get(key)
  }

  def snapshot(): Seq[(K, V)] = synchronized {
    map.toSeq
  }

  def values(): Seq[V] = synchronized {
    map.values.toSeq
  }

}
