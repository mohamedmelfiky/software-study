package practice

import kotlin.math.ceil

fun main() {
//    testOrganizingContainers()
//    testServiceLane()
    testSuperReducedString()
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