package binarySearch

fun binarySearch(key: Int, array: IntArray, min: Int, max: Int): Int {
    if (min > max) return -1
    val midIndex = (max + min) / 2
    val midItem = array[midIndex]
    if (key == midItem) return midIndex
    if (key < midItem) return binarySearch(key, array, min, midIndex - 1)
    if (key > midItem) return binarySearch(key, array, midIndex + 1, max)
    return -1
}

fun binary(key: Int, array: IntArray): Int {
    var min = 0
    var max = array.lastIndex
    while (min <= max) {
        val midIndex = (max + min) / 2
        val midItem = array[midIndex]
        if (key == midItem) return midIndex
        if (key < midItem) max = midIndex - 1
        if (key > midItem) min = midIndex + 1
    }
    return -1
}

fun main() {
    val sortedArray = IntArray(20) { index -> index }
//    val notExist = binarySearch(21, sortedArray, 0, sortedArray.lastIndex)
//    val index0 = binarySearch(0, sortedArray, 0, sortedArray.lastIndex)
//    val index19 = binarySearch(19, sortedArray, 0, sortedArray.lastIndex)
//    val index5 = binarySearch(5, sortedArray, 0, sortedArray.lastIndex)

    val notExist = binary(21, sortedArray)
    val index0 = binary(0, sortedArray)
    val index19 = binary(19, sortedArray)
    val index5 = binary(5, sortedArray)

    println(notExist)
    println(index0)
    println(index19)
    println(index5)
}