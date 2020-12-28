package practice

import sorting.mergeSort
import java.math.BigInteger
import java.util.*
import kotlin.Comparator
import kotlin.math.ceil

fun main() {
//    testOrganizingContainers()
//    testServiceLane()
//    testSuperReducedString()
//    testCavityMap()
    testBigSorting()
}

fun workbookTest() {
    val result = workbook(
        10,
        5,
        arrayOf(3, 8, 15, 11, 14, 1, 9, 2, 24, 31)
    )
    println(result)

//    println(specialInChapter(6, 6, 3, 1))
}
fun workbook(chapters: Int, problemsPerPage: Int, problemsPerChapter: Array<Int>): Int {
    var pages = 0
    var specials = 0

    for (chapter in 0 until chapters) {
        val problems = problemsPerChapter[chapter]
        val chapterPages = ceil(problems.toFloat() / problemsPerPage).toInt()
        val fromPage = pages + 1
        pages += chapterPages
        specials += specialInChapter(fromPage, pages, problemsPerPage, problems)
    }

    return specials
}
fun specialInChapter(startPage: Int, endPage: Int, problemsPerPage: Int, problems: Int): Int {
    var specials = 0
    for (page in startPage..endPage) {
        val start = ((page - startPage) * problemsPerPage) + 1
        val end = (start + problemsPerPage - 1).coerceAtMost(problems)
        println("$start $end")
        if (page in start..end) specials += 1
    }
    println(specials)
    return specials
}

// Organizing Containers of Balls
// https://www.hackerrank.com/challenges/organizing-containers-of-balls/problem
fun testOrganizingContainers() {
    val result = organizingContainers(
        listOf(
            listOf(1, 1),
            listOf(1, 1),
        )
    )
    println(result)
}
fun organizingContainers(containers: List<List<Int>>): String {
    println(containers)
    val rows = containers.map { it.sum() }.sorted()
    val columns = containers.reduce { acc, ints -> acc.zip(ints) { xv, yv -> xv + yv } }.sorted()
    println(rows)
    println(columns)
    return if (rows == columns) "Possible" else "Impossible"
}

// Complete the serviceLane function below.
fun testServiceLane() {
    val result = serviceLane(
        8,
        arrayOf(2, 3, 1, 2, 3, 2, 3, 3),
        arrayOf(
            arrayOf(0, 3),
            arrayOf(4, 6),
            arrayOf(6, 7),
            arrayOf(3, 5),
            arrayOf(0, 7),
        )
    )

    println(result.toList())
}
fun serviceLane(n: Int, width: Array<Int>, cases: Array<Array<Int>>): Array<Int> {
    val result = Array(cases.size) { 0 }
    cases.forEachIndexed { index, case ->
        val maxWidth = width.sliceArray(case[0]..case[1]).minOrNull() ?: 0
        result[index] = maxWidth
    }
    return result
}

fun testSuperReducedString() {
//    val result = superReducedString("aaabccddd")
//    val result = superReducedStringRegex("aaabccddd")
    val result = superReducedStringStack("aaabccddd")
    println(result)
}
fun superReducedString(s: String): String {
    val result = findAndDelete(s)
    return if (result.isEmpty()) "Empty String" else result
}
fun superReducedStringRegex(s: String): String {
    val regex = Regex("(.)\\1")
    var result = s
    var isFound = regex.containsMatchIn(result)
    while (isFound) {
        result = result.replace(regex, "")
        isFound = regex.containsMatchIn(result)
    }
    return if (result.isEmpty()) "Empty String" else result
}
fun superReducedStringStack(s: String): String {
//    val stack = ArrayDeque<Char>()
//    s.forEach { char ->
//        val last = stack.peekFirst()
//        if (last != null && last == char) {
//            stack.pop()
//        } else {
//            stack.push(char)
//        }
//    }
    val result = buildString {
        s.forEach { char ->
            val last = lastOrNull()
            if (last == char) {
                deleteAt(lastIndex)
            } else {
                append(char)
            }
        }
    }
//    val result = stack.joinToString("")
    return if (result.isEmpty()) "Empty String" else result
}
fun findAndDelete(input: String, startIndex: Int = 0): String {
    if (startIndex == input.lastIndex) return input

    var index = if (startIndex < 0) 0 else startIndex
    while (index < input.length) {
        val currentChar = input[index]
        val nextChar = input.getOrNull(index + 1)
        if (nextChar != null && nextChar == currentChar) {
            return if (index + 2 < input.length) {
                findAndDelete(input.substring(0, index) + input.substring(index + 2, input.length), index - 1)
            } else {
                findAndDelete(input.substring(0, index), index - 1)
            }
        }
        index++
    }
    return input
}

// https://www.hackerrank.com/challenges/cavity-map/problem
// Complete the cavityMap function below.
fun testCavityMap() {
    println(cavityMap(
        arrayOf(
            "1112",
            "1912",
            "1892",
            "1234",
        )
    ).toList())
}
fun cavityMap(grid: Array<String>): Array<String> {
    val result = mutableListOf<String>()
    result.add(grid.first())
    for (rowIndex in 1 until grid.lastIndex) {
        val row = grid[rowIndex]
        val rowResult = StringBuilder()
        rowResult.append(row.first())
        for (columnIndex in 1 until row.lastIndex) {
            val currentCell = row[columnIndex]
            val adjacentGrid = arrayOf(
                row.getOrNull(columnIndex - 1),
                row.getOrNull(columnIndex + 1),
                grid.getOrNull(rowIndex - 1)?.getOrNull(columnIndex),
                grid.getOrNull(rowIndex + 1)?.getOrNull(columnIndex)
            )
            rowResult.append(if (isCavity(currentCell, adjacentGrid)) "X" else currentCell)
        }
        if (row.length > 1) rowResult.append(row.last())
        result.add(rowResult.toString())
    }
    if (grid.size > 1) result.add(grid.last())
    return result.toTypedArray()
}
fun isCavity(cell: Char, adjacent: Array<Char?>): Boolean {
    val notNullAdjacent = adjacent.filterNotNull()
    if (notNullAdjacent.size < 3) return false
    notNullAdjacent.forEach {
        if (it >= cell) return false
    }
    return true
}

// https://www.hackerrank.com/challenges/big-sorting/problem
fun testBigSorting() {
    val result = bigSorting(
        arrayOf(
            "31415926535897932384626433832795",
            "1",
            "3",
            "10",
            "3",
            "5",
            "840019527773497580934729276645605506991983781882692159573234547483268388128440851549106451924206925757662462952401655087944126606208097780490296237068587655390161173278994345799053571031",
            "678385232869337681809044060978430572838297674277263002856530311883128404119111546097719237915618040122234925542881864106697038702066928061742692058058697082237879050994587912015670367224",
            "840019527773497580934729276645605506991983781882692159573234547483268388128440851549106451924206925757662462952401655087944126606208097780490296237068587655390161173278994345799053571032",
            )
    )

    println(result.joinToString())
    println(
        BigInteger("840019527773497580934729276645605506991983781882692159573234547483268388128440851549106451924206925757662462952401655087944126606208097780490296237068587655390161173278994345799053571031") > BigInteger("678385232869337681809044060978430572838297674277263002856530311883128404119111546097719237915618040122234925542881864106697038702066928061742692058058697082237879050994587912015670367224")
    )

    println("840019527773497580934729276645605506991983781882692159573234547483268388128440851549106451924206925757662462952401655087944126606208097780490296237068587655390161173278994345799053571031\") > BigInteger(\"678385232869337681809044060978430572838297674277263002856530311883128404119111546097719237915618040122234925542881864106697038702066928061742692058058697082237879050994587912015670367224".windowed(19, 19))
}
fun bigSorting(unsorted: Array<String>): Array<String> {
    unsorted.sortWith(sortComparable)
    unsorted.joinToString()
    return unsorted
}

val sortComparable = Comparator<String> { one, two ->
    if (one.length == two.length) one.compareTo(two) else one.length.compareTo(two.length)
}
fun recursive(one: String, two: String): Int {
    val hash1 = one.hashCode()
    val hash2 = two.hashCode()
    if (hash1 == hash2) return 0
    if (one.length == 1) return one.toInt().compareTo(two.toInt())
    val mid = one.length / 2
    val oneFirstSub = one.substring(0, mid)
    val twoFirstSub = two.substring(0, mid)
    if (oneFirstSub.hashCode() == twoFirstSub.hashCode()) return recursive(one.substring(mid), two.substring(mid))
    return recursive(oneFirstSub, twoFirstSub)
}

fun stringComparable(one: String, two: String): Int {
    if (one.length < two.length) return -1
    if (one.length > two.length) return 1
    one.forEachIndexed { index, char ->
        val secondChar = two[index]
        val compare = char.compareTo(secondChar)
        if (compare != 0) return compare
    }
    return 0
}