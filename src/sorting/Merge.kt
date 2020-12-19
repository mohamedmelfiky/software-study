package sorting

import kotlin.Comparator

/**
 * time: best -> Ω(NLogN) average -> Θ(NLogN) worst -> O(NLogN)
 * space: O(N) in-place
 * stability: stable
 * arrays: works lists: works
 * offline algorithm:
 * notes:
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
    names.mergeSort { it.firstName }
    println(names)
    names.mergeSort { it.lastName }
    println(names)
}

fun <T, R: Comparable<R>> MutableList<T>.mergeSort(selector: (T) -> R?) {
    if (size < 2) return
    val comparable = compareBy(selector)

    val low = 0
    val high = lastIndex
    val aux = MutableList(size) { this[it] }
    sort(aux, low, high, comparable)
}

private fun <T> MutableList<T>.sort(
    aux: MutableList<T>,
    low: Int,
    high: Int,
    comparator: Comparator<T>
) {
    if (high <= low) return
    val mid = low + (high - low) / 2
    sort(aux, low, mid, comparator)
    sort(aux, mid + 1, high, comparator)
    merge(aux, low, mid, high, comparator)
}

private fun <T> MutableList<T>.merge(
    aux: MutableList<T>,
    low: Int,
    mid: Int,
    high: Int,
    comparator: Comparator<T>
) {
    for (index in low..high) {
        aux[index] = this[index]
    }

    fun less(one: T, two: T): Boolean {
        return comparator.compare(one, two) < 0
    }

    var i = low
    var j = mid + 1
    for (k in low..high) {
        this[k] = when {
            i > mid -> aux[j++]
            j > high -> aux[i++]
            less(aux[j], aux[i]) -> aux[j++]
            else -> aux[i++]
        }
    }
}