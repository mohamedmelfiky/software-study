package sorting

import kotlin.math.abs

/**
 * time: best -> Ω(N) average -> Θ(N2) worst -> O(N2)
 * space: O(1) in-place
 * stability: stable
 * arrays: works lists: works
 * online algorithm: considers one input element per iteration and produces a partial solution without considering future elements
 * notes: more efficient than selection and bubble sort
*/

fun main() {
    val names = mutableListOf(
        PersonName("Sama", "Caldwell"),
        PersonName("Radhika", "Kent"),
        PersonName("Lacie", "Caldwell"),
        PersonName("Rodrigo", "Kent"),
        PersonName("Keri", "Caldwell"),
        PersonName("Ayisha", "Kent"),
        PersonName("Shanai", "Caldwell"),
        PersonName("Gavin", "Caldwell"),
        PersonName("Cynthia", "Kent"),
    )
    names.binaryInsertionSort { it.firstName }
    println(names)
    names.binaryInsertionSort { it.lastName }
    println(names)
}

fun <T, R: Comparable<R>> MutableList<T>.insertionSort(selector: (T) -> R?) {
    if (size < 2) return
    val comparable = compareBy(selector)

    for (index in 1 until size) {
        var currentIndex = index
        fun isLessThanPrev() = comparable.compare(this[currentIndex], this[currentIndex - 1]) < 0
        while (currentIndex > 0 && isLessThanPrev()) {
            swap(currentIndex, currentIndex - 1)
            currentIndex--
        }
    }
}

fun <T, R: Comparable<R>> MutableList<T>.binaryInsertionSort(selector: (T) -> R?) {
    if (size < 2) return
    val comparable = compareBy(selector)

    for (index in 1 until size) {
        val currentItem = this[index]
        val isLessThanPrev = comparable.compare(this[index], this[index - 1]) < 0
        if (isLessThanPrev) {
            var searchIndex = 1
            var fromIndex = 0
            while (searchIndex >= 0) {
                searchIndex = binarySearch(fromIndex = fromIndex, toIndex = index) { comparable.compare(it, currentItem) }
                fromIndex++
            }
            val rightIndex = abs(searchIndex) - 1
            removeAt(index)
            add(rightIndex, currentItem)
        }
    }
}

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}