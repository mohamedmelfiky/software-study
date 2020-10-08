package queue

interface Queue<T> {
    fun empty(): Boolean
    fun enqueue(value: T)
    fun dequeue(): T?
}