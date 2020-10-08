package arrays

import kotlin.math.sqrt

fun main() {
    val vector = Vector<Int>()
    println(vector)
    vector.push(1)
    println(vector)
    vector.push(5)
    println(vector)
    vector.push(2)
    println(vector)
    vector.push(5)
    println(vector)
    vector.push(3)
    println(vector)
    vector.push(5)
    println(vector)
    vector.push(4)
    println(vector)
    vector.push(5)
    println(vector)
    vector.insert(1, 20)
    println(vector)
    vector.prepend(100)
    println(vector)
    vector.pop()
    println(vector)
    vector.delete(0)
    println(vector)
    vector.find(500)
    vector.remove(5)
    println(vector)
    vector.pop()
    println(vector)
    vector.pop()
    println(vector)
    vector.pop()
    println(vector)
    vector.push(6)
    println(vector)
    vector.push(7)
    println(vector)
    vector.push(8)
    println(vector)
    vector.push(8)
    println(vector)
    vector.push(8)
    println(vector)
    vector.push(8)
    println(vector)

    vector.insert(8, 9)
}

fun array_test() {
    val array = IntArray(10) { it }
//    println(array[0])
//    array[0] = 1
//    array[5] = 5
//    println(array[5])
//    array.reverse()
    for (index in 0 until array.size / 2) {
        val temp = array[index]
        array[index] = array[array.lastIndex - index]
        array[array.lastIndex - index] = temp
//        array[index] = array[index] + array[array.lastIndex - index]
//        array[array.lastIndex - index] = array[index] - array[array.lastIndex - index]
//        array[index] = array[index] - array[array.lastIndex - index]
    }
    println(array.joinToString())
}

class Vector<T> {

    var capacity = 16
        private set
    var size = 0
        private set

    private var array = MutableList<T?>(capacity) { null }

    private fun resize(newCapacity: Int) {
        capacity = newCapacity
//        val temp = array
        array = MutableList(newCapacity) { index -> if (index < size) array[index] else null }
//        for (index in 0 until size) array[index] = temp[index]
    }

    private fun isReachedCapacity(): Boolean {
        return size == capacity
    }

    private fun incrementSize() {
        size++
        if (isReachedCapacity()) resize(capacity * 2)
    }

    private fun decrementSize() {
        size--
        if (capacity / size == 4) resize(capacity / 2)
    }

    fun isEmpty(): Boolean {
        return size == 0
    }

    fun at(index: Int) { array[index] }

    fun push(item: T) {
        array[size] = item
        incrementSize()
    }

    fun insert(index: Int, item: T) {
        val tempArray = MutableList(size - index) { array[it + index] }
        array[index] = item
        for (currentIndex in 0..tempArray.lastIndex) {
            array[currentIndex + index + 1] = tempArray[currentIndex]
        }
        incrementSize()
    }

    fun prepend(item: T) {
        insert(0, item)
    }

    fun pop(): T? {
        val temp = array[size - 1]
        array[size - 1] = null
        decrementSize()
        return temp
    }

    fun delete(index: Int) {
        val tempArray = MutableList(size - index - 1) { array[it + index + 1] }
        for (currentIndex in 0..tempArray.lastIndex) {
            array[currentIndex + index] = tempArray[currentIndex]
        }
        array[size - 1] = null
        decrementSize()
    }

    fun remove(item: T) {
        for (index in 0 until size) {
            if (array[index] == item) delete(index)
        }
    }

    fun find(item: T): Int {
        for (index in 0 until size) {
            if (array[index] == item) return index
        }

        return -1
    }

    override fun toString(): String {
        return "$array size: $size capacity: $capacity"
    }
}

private fun arraysBuilders() {
    // https://stackoverflow.com/a/45094889/6484309
    val array1 = arrayOf(1)
    val array2 = arrayOfNulls<String>(10)
    val array3 = emptyArray<String>()
    val array4 = Array(10) { "" }
    val array5 = IntArray(10)
}

private fun allPrimes(n: Int) {
    //Sieve of Eratosthenes
    val prime = BooleanArray(n + 1) { index -> index > 1 }
    (2..sqrt(n.toFloat()).toInt()).forEach {

    }
    prime.forEach { println(it.toString()) }
}