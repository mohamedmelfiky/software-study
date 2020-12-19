package sorting

/**
 * time: best -> Ω(NLogN) average -> Θ(NLogN) worst -> O(N2)
 * space: O(logN) in-place (partitioning O(1) depth of recursion (logN))
 * stability: not stable
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
    names.quickSort { it.firstName }
    println(names)
//    names.mergeSort { it.lastName }
//    println(names)
}

fun <T, R: Comparable<R>> MutableList<T>.quickSort(selector: (T) -> R?) {
    if (size < 2) return
    val comparable = compareBy(selector)

    // shuffle grantees the worst case not happen
    shuffle()
    sort(0, lastIndex, comparable)
}

private fun <T> MutableList<T>.sort(
    low: Int,
    high: Int,
    comparator: Comparator<T>
) {
    // base case
    if (high <= low) return

//    val partitioningElement = partition(low, high, comparator)
    val partitioningElement = threeWayPartition(low, high, comparator)
    sort(low, partitioningElement.first - 1, comparator)
    sort(partitioningElement.second + 1, high, comparator)
}

private fun <T> MutableList<T>.partition(
    low: Int,
    high: Int,
    comparator: Comparator<T>
): Pair<Int, Int> {
    val element = this[low]
    var smallPointer = low + 1
    var bigPointer = high

    fun less(one: T, two: T): Boolean {
        return comparator.compare(one, two) < 0
    }

    while (true) {
        while (less(this[smallPointer], element)) {
            if (smallPointer == high) break
            smallPointer++
        }

        while (less(element, this[bigPointer])) {
            if (bigPointer == low) break
            bigPointer--
        }

        if (smallPointer >= bigPointer) break
        swap(smallPointer, bigPointer)
    }

    swap(low, bigPointer)
    return bigPointer to bigPointer
}

private fun <T> MutableList<T>.threeWayPartition(
    low: Int,
    high: Int,
    comparator: Comparator<T>
): Pair<Int, Int> {
    val element = this[low]

    var gt = high
    var lt = low
    var i = low
    while (i <= gt) {
        val cmp = comparator.compare(this[i], element)
        when {
            cmp < 0 -> swap(lt++, i++)
            cmp > 0 -> swap(i, gt--)
            else -> i++
        }
    }

    return lt to gt
}