package sorting

/**
 * time: best -> Ω(N2) average -> Θ(N2) worst -> O(N2)
 * time: best -> Ω(N) average -> Θ(N2) worst -> O(N2) in case of optimized.
 * space: O(1) in-place
 * stability: stable
 * arrays: works lists: works
 * offline algorithm
 * notes: is not a practical sorting algorithm
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
    names.bubbleSort { it.firstName }
    println(names)
    names.bubbleSort { it.lastName }
    println(names)

//    val namesSorted = mutableListOf(
//        PersonName("Ayisha", "Kent"),
//        PersonName("Cynthia", "Kent"),
//        PersonName("Gavin", "Caldwell"),
//        PersonName("Keri", "Caldwell"),
//        PersonName("Lacie", "Caldwell"),
//        PersonName("Radhika", "Kent"),
//        PersonName("Rodrigo", "Kent"),
//        PersonName("Sama", "Caldwell"),
//        PersonName("Shanai", "Caldwell"),
//    )
//    namesSorted.bubbleSort { it.firstName }
//    println(names)
}

fun <T, R: Comparable<R>> MutableList<T>.bubbleSort(selector: (T) -> R?) {
    if (size < 2) return
    val comparable = compareBy(selector)

    for (last in lastIndex downTo 0) {
        var isSwapped = false
        for (index in 0 until last) {
            val result = comparable.compare(this[index], this[index + 1])
            if (result > 0) {
                swap(index, index + 1)
                isSwapped = true
            }
        }
        if (!isSwapped) break
    }
}