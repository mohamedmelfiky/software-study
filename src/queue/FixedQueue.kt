package queue

class FixedQueue<T>(private val size: Int) : Queue<T> {

    private val array = MutableList<T?>(size + 1) { null }
    private var nextEnqueue = 0
    private var nextDequeue = 0

    override fun empty(): Boolean {
        return nextEnqueue == nextDequeue
    }

    override fun enqueue(value: T) {
        if (full()) return
        array[nextEnqueue] = value
        nextEnqueue = (nextEnqueue + 1) % array.size
    }

    override fun dequeue(): T? {
        if (empty()) return null
        val value = array[nextDequeue]
        array[nextDequeue] = null
        nextDequeue = (nextDequeue + 1) % array.size
        return value
    }

    fun full(): Boolean {
        return (nextEnqueue + 1) % array.size == nextDequeue
    }

    fun debug() {
        println("$array size:${array.size}")
    }

}