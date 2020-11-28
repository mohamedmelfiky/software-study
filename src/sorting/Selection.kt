package sorting

/**
 * time: best -> Ω(N2) average -> Θ(N2) worst -> O(N2)
 * space: O(1) in-place
 * stability: not stable
 * arrays: works lists: works
 * offline algorithm: require access to the entire input
 * notes: more efficient than selection and bubble sort
 */

fun main() {
//    val list = mutableListOf("Sama", "Radhika", "Lacie", "Rodrigo", "Keri", "Ayisha", "Shanai")
//    list.selectionSort()
//    list.sort()
//    println(list)

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
    names.selectionSort { it.firstName }
    println(names)
    names.sortBy { it.lastName }
    println(names)
}

fun <T: Comparable<T>> MutableList<T>.selectionSort() {
    if (size < 2) return
    forEachIndexed { index, item ->
        var currentMinimumIndex = index
        var currentMinimum = item

        for (movingIndex in index until size) {
            val current = this[movingIndex]
            if (current < currentMinimum) {
                currentMinimumIndex = movingIndex
                currentMinimum = current
            }
        }

        if (currentMinimum != item) {
            this[index] = this[currentMinimumIndex]
            this[currentMinimumIndex] = item
        }
    }
}

// time: O(N2) O(N2) O(N2) space: O(1) not stable arrays: works lists: works
fun <T, R : Comparable<R>> MutableList<T>.selectionSort(selector: (T) -> R?) {
    if (size < 2) return
    val comparable = compareBy(selector)

    forEachIndexed { index, item ->
        var currentMinimumIndex = index

        for (movingIndex in index + 1 until size) {
            val current = this[movingIndex]
            val compare = comparable.compare(current, this[currentMinimumIndex])
            if (compare < 0) {
                currentMinimumIndex = movingIndex
            }
        }

        if (currentMinimumIndex != index) {
            this[index] = this[currentMinimumIndex]
            this[currentMinimumIndex] = item
        }
    }
}