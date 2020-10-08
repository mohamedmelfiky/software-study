package linkedList

fun main() {
    val singleLinkedList = SingleLinkedList<Int>()
    with(singleLinkedList) {
        pushBack(11)

        pushFront(10)
        pushFront(9)
        pushFront(8)
        pushFront(7)
        pushFront(6)
        pushFront(5)

        pushBack(12)

        insert(0, 100)
        insert(2, 200)
        insert(size() - 1, 300)

        erase(0)
        erase(1)
        erase(size() - 1)

        printAll()
        println("value 2 from end: ${valueFromEnd(2)}")

        reverse()
        printAll()

        removeValue(9)
        printAll()
        removeValue(12)
        printAll()
        removeValue(5)
        printAll()
        pushFront(11)
        pushFront(11)
        pushFront(11)
        printAll()
        removeValue(11)
        printAll()
//        println("value at index 0: ${valueAt(0)}")
//        println("value at index 5: ${valueAt(5)}")
//        println("value at front: ${front()}")
//        println("value at front: ${back()}")

//        popFront()
//        printAll()
//        popFront()
//        popFront()
//        popFront()
//        popFront()
//        popFront()
//        popFront()
//        printAll()
//
//        pushBack(13)
//        printAll()
//        popBack()
//        printAll()
//        popBack()
//        printAll()
//        popBack()
//        printAll()
    }
}