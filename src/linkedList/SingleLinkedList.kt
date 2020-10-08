package linkedList

import java.security.InvalidParameterException

class SingleLinkedList<T>: LinkedList<T> {


    private var head: SingleListNode<T>? = null

    // returns number of data elements in list
    private var size = 0

    override fun size(): Int {
        return size
    }

    // returns true if empty
    override fun empty(): Boolean {
        return size == 0
    }

    // returns the value of the nth item (starting at 0 for first)
    override fun valueAt(index: Int): T? {
        if (index < 0) throw InvalidParameterException()
        if (index > size - 1) throw IndexOutOfBoundsException()
        if (empty()) return null
        var pointer = head
        (0 until index).forEach { _ -> pointer = pointer?.next }
        return pointer?.value
    }

    // adds an item to the front of the list
    override fun pushFront(value: T) {
        val node = SingleListNode(value = value, next = head)
        head = node
        size++
    }

    // remove front item and return its value
    override fun popFront() {
        if (empty()) return
        head = head?.next
        size--
    }

    override fun pushBack(value: T) {
        if (empty()) {
            pushFront(value)
            return
        }
        val insertedNode = SingleListNode(value, next = null)
        var lastNode = head
        while (lastNode?.next != null) {
            lastNode = lastNode.next
        }
        lastNode?.next = insertedNode
        size++
    }

    // removes end item and returns its value
    override fun popBack() {
        if (empty()) return
        if (size == 1) {
            head = null
            size--
            return
        }
        var lastNode = head
        (1 until size - 1).forEach { _ -> lastNode = lastNode?.next }
        lastNode?.next = null
        size--
    }

    // get value of front item
    override fun front(): T? {
        return head?.value
    }

    // get value of end item
    override fun back(): T? {
        var lastNode = head
        while (lastNode?.next != null) {
            lastNode = lastNode.next
        }
        return lastNode?.value
    }

    // insert value at index, so current item at that index is pointed to by new item at index
    override fun insert(index: Int, value: T) {
        if (index < 0) throw InvalidParameterException()
        if (index > size - 1) throw IndexOutOfBoundsException()
        if (index == 0) return pushFront(value)
        if (index == size - 1) return pushBack(value)
        val insertedNode = SingleListNode(value, next = null)
        var nodePointToIndexedNode = head
        repeat(index - 1) {
            nodePointToIndexedNode = nodePointToIndexedNode?.next
        }
        insertedNode.next = nodePointToIndexedNode?.next
        nodePointToIndexedNode?.next = insertedNode
        size++
    }

    // removes node at given index
    override fun erase(index: Int) {
        if (index < 0) throw InvalidParameterException()
        if (index > size - 1) throw IndexOutOfBoundsException()
        if (index == 0) return popFront()
        if (index == size - 1) return popBack()
        var nodePointToIndexedNode = head
        repeat(index - 1) {
            nodePointToIndexedNode = nodePointToIndexedNode?.next
        }
        nodePointToIndexedNode?.next = nodePointToIndexedNode?.next?.next
        size--
    }

    // returns the value of the node at nth position from the end of the list
    override fun valueFromEnd(index: Int): T? {
        val indexFromEnd = size - 1 - index
        return valueAt(indexFromEnd)
    }

    // reverses the list
    override fun reverse() {
        if (size <= 1) return

        var tempHead: SingleListNode<T>? = null
        var tempNode = head
        while (tempNode != null) {
            // move to next node
            head = head?.next
            // push in the temp head
            // change the prev node next pointer
            tempNode.next = tempHead
            // make the temp head point to the prev node
            tempHead = tempNode
            tempNode = head
        }
        head = tempHead
    }

    // removes the first item in the list with this value
    override fun removeValue(value: T) {
        if (head?.value == value) {
            head = head?.next
            size--
        } else {
            var currentNode = head
            while (currentNode?.next != null) {
                if (currentNode.next?.value == value) {
                    currentNode.next = currentNode.next?.next
                    size--
                    break
                }

                currentNode = currentNode.next
            }
        }
    }

    fun printAll() {
        print("Size: $size, ")
        print("List: [")
        var iterator = head
        while (iterator != null) {
            print(iterator.value)
            iterator = iterator.next
            if (iterator != null) print(", ")
        }
        print("]")
        println()
    }

}