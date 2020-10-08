package linkedList

interface LinkedList<T> {
    fun size(): Int
    fun empty(): Boolean
    fun pushFront(value: T)
    fun popFront()
    fun pushBack(value: T)
    fun popBack()
    fun front(): T?
    fun back(): T?
    fun insert(index: Int, value: T)
    fun valueAt(index: Int): T?
    fun valueFromEnd(index: Int): T?
    fun erase(index: Int)
    fun removeValue(value: T)
    fun reverse()
}