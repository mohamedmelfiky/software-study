package queue

import linkedList.LinkedList
import linkedList.SingleLinkedList

class QueueImpl<T> : Queue<T> {

    private val list: LinkedList<T> = SingleLinkedList()

    override fun empty(): Boolean {
        return list.empty()
    }

    override fun enqueue(value: T) {
        list.pushBack(value)
    }

    override fun dequeue(): T? {
        val value = list.front()
        list.popFront()
        return value
    }

}