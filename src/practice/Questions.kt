package practice

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.system.measureTimeMillis

fun main() {
//    testReverseArray()
//    testDynamicArray()
//    testRotateLeft()
//    testIsBalanced()
//    testSparseArrays()
//    testCycleDetection()
//    testQueueUsingTwoStacks()
//    testAlternatingCharacters()
//    testMinimumDistances()
//    testQueensAttack()
//    testNonDivisibleSubset()
    testTimeInWords()
}

// Arrays - DS
// https://www.hackerrank.com/challenges/arrays-ds/problem
fun testReverseArray() {
    val testArray = arrayOf(1, 4, 3, 2, 5)
    val reversed = reverseArray(testArray)
    println(reversed.joinToString(" "))
}
fun reverseArray(a: Array<Int>): Array<Int> {
    val midPoint = a.lastIndex / 2
    var tail = a.lastIndex
    (0..midPoint).forEach { head ->
        val temp = a[head]
        a[head] = a[tail]
        a[tail] = temp
        tail--
    }
    return a
}

// Dynamic Array
// https://www.hackerrank.com/challenges/dynamic-array/problem
fun testDynamicArray() {
    val noOfSequences = 2
    val queries = arrayOf(
        arrayOf(1, 924823508, 815040548),
        arrayOf(1, 999122842, 875948179),
        arrayOf(1, 293905693, 460594151),
        arrayOf(2, 834422454, 862948046),
        arrayOf(1, 851420399, 139106032),
        arrayOf(1, 953820879, 695067076),
        arrayOf(2, 692599429, 950215041)
    )

    val time = measureTimeMillis {
        dynamicArray(noOfSequences, queries)
    }
    val result = dynamicArray(noOfSequences, queries)
    println("time: $time")
    println(result.joinToString("\n"))
}
fun dynamicArray(n: Int, queries: Array<Array<Int>>): Array<Int> {
    val seqList = List(n) { ArrayList<Int>(n) }
    val result = ArrayList<Int>()
    var lastAnswer = 0
    for (query in queries) {
        val seq = (query[1] xor lastAnswer) % n
        if (query[0] == 1) {
            seqList[seq].add(query[2])
        } else {
            lastAnswer = seqList[seq][query[2] % seqList[seq].size]
            result.add(lastAnswer)
        }
    }
    return result.toTypedArray()
}

// Rotate left
// https://www.hackerrank.com/challenges/array-left-rotation/problem
fun testRotateLeft() {
    val array = Array(5) { index -> index + 1 }
    val result = rotateLeft(4, array)
    println(array.joinToString(" "))
    println(result.joinToString(" "))
}
fun rotateLeft(d: Int, arr: Array<Int>): Array<Int> {
    // Write your code here
    // O(N) space: O(N)
    return Array(arr.size) { index ->
        val valueIndex = (index + d) % arr.size
        arr[valueIndex]
    }
}

// Balanced Brackets
// https://www.hackerrank.com/challenges/balanced-brackets/problem
fun testIsBalanced() {
    val input = "({}([][]))[]()"
    val result = isBalanced(input)
    println(result)
}
fun isBalanced(s: String): String {
    if (s.length % 2 != 0) return "NO"
    val charMap = mapOf(
        '{' to '}',
        '[' to ']',
        '(' to ')'
    )
    val stack = Stack<Char>()
    s.forEach { char ->
        if (char in charMap.keys) {
            stack.push(char)
        } else {
            if (stack.empty()) return "NO"
            val value = stack.pop()
            val closed = charMap[value]
            if (char != closed) {
                return "NO"
            }
        }
    }

    return if (stack.isEmpty()) {
        "YES"
    } else {
        "NO"
    }
}

// Sparse Arrays
// https://www.hackerrank.com/challenges/sparse-arrays/problem
fun testSparseArrays() {
    val strings = arrayOf("aba", "baba", "aba", "xzxb")
    val queries = arrayOf("aba", "xzxb", "ab")

    val result = matchingStrings(strings, queries)
    println(result.joinToString("\n"))
}
fun matchingStrings(strings: Array<String>, queries: Array<String>): Array<Int> {
    // brute force time: O(nq) space: O(q)
//    val resultArray = Array(queries.size) { 0 }
//    queries.forEachIndexed { index, query ->
//        var currentOccurrences = 0
//        strings.forEach { string ->
//            if (query == string) currentOccurrences++
//        }
//        resultArray[index] = currentOccurrences
//    }
//    return resultArray

    // time: O(N+Q) space: O(Q+Q)
    val resultArray = Array(queries.size) { 0 }
    val countMap = mutableMapOf<String, Int>()
    strings.forEach { string ->
        val currentCount = countMap[string] ?: 0
        countMap[string] = currentCount + 1
    }
    queries.forEachIndexed { index, query ->
        val queryCurrentCount = countMap[query] ?: 0
        resultArray[index] = queryCurrentCount
    }
    return resultArray
}

// Cycle Detection
// https://www.hackerrank.com/challenges/detect-whether-a-linked-list-contains-a-cycle/problem
class SinglyLinkedListNode(val data: Int, var next: SinglyLinkedListNode?)
fun testCycleDetection() {
    val node3 = SinglyLinkedListNode(3, null)
    val node2 = SinglyLinkedListNode(2, node3)
    val head = SinglyLinkedListNode(1, node2)
    node3.next = node2
    println(hasCycle(head))
}
fun hasCycle(head: SinglyLinkedListNode?): Boolean {
    val visitedNodes = mutableMapOf<SinglyLinkedListNode, Boolean>()
    if (head != null) visitedNodes[head] = true
    var currentNode = head?.next
    while (currentNode != null) {
        val isVisited = visitedNodes[currentNode]
        if (isVisited == true) return true
        visitedNodes[currentNode] = true
        currentNode = currentNode.next
    }
    return false
}

// Find Merge Point of Two Lists
// https://www.hackerrank.com/challenges/find-the-merge-point-of-two-joined-linked-lists/problem
fun testFindMergeNode() {

}
fun findMergeNode(head1: SinglyLinkedListNode, head2: SinglyLinkedListNode): Int {
    val map: HashSet<SinglyLinkedListNode> = HashSet()
    var listOneNode: SinglyLinkedListNode? = head1
    while (listOneNode != null) {
        map.add(listOneNode)
        listOneNode = listOneNode.next
    }
    var listTwoNode: SinglyLinkedListNode? = head2
    while (listTwoNode != null) {
        val isExist = map.contains(listTwoNode)
        if (isExist) {
            return listTwoNode.data
        }
        listTwoNode = listTwoNode.next
    }
    return -1
}

// Tree: Preorder Traversal
// https://www.hackerrank.com/challenges/tree-preorder-traversal/problem
class Node {
    var data = 0
    var left: Node? = null
    var right: Node? = null
}
fun testPreorderTraversal() {

}
fun preorderTraversalRecursive(root: Node?) {
    if (root == null) return
    print("${root.data} ")
    preorderTraversalRecursive(root.left)
    preorderTraversalRecursive(root.right)
}
fun preorderTraversalStack(root: Node) {
    val stack = Stack<Node>()
    stack.push(root)
    while (!stack.empty()) {
        val current = stack.pop()
        print("${root.data} ")
        if (current.right != null) stack.push(current.right)
        if (current.left != null) stack.push(current.left)
    }
}

// Queue using Two Stacks
// https://www.hackerrank.com/challenges/queue-using-two-stacks/problem
fun testQueueUsingTwoStacks() {
    queueUsingTwoStacks()
}
class QueueWithTwoStacks {
    private val enqueueStack = Stack<Int>()
    private val dequeueStack = Stack<Int>()

    fun enqueue(value: Int) {
        enqueueStack.push(value)
    }

    fun dequeue(): Int {
        moveEnqueueToDequeue()
        return dequeueStack.pop()
    }

    fun peek() {
        moveEnqueueToDequeue()
        println(dequeueStack.peek())
    }

    private fun moveEnqueueToDequeue() {
        if (dequeueStack.isEmpty()) {
            while (enqueueStack.isNotEmpty()) {
                dequeueStack.push(enqueueStack.pop())
            }
        }
    }
}
fun queueUsingTwoStacks() {
    val queue = QueueWithTwoStacks()
    generateSequence(::readLine).drop(1).toList().onEach { query ->
        val querySplit = query.split(" ")
        when (querySplit.first().toInt()) {
            1 -> queue.enqueue(querySplit[1].toInt())
            2 -> queue.dequeue()
            3 -> queue.peek()
        }
    }
}

// Alternating Characters
// https://www.hackerrank.com/challenges/alternating-characters/problem
fun testAlternatingCharacters() {
    val result = alternatingCharacters("AABBBBABABAABABBAA")
    println(result)
}
fun alternatingCharacters(s: String): Int {
    var deletionNo = 0
    s.forEachIndexed { index, c ->
        if (c == s.getOrNull(index - 1)) deletionNo++
    }
    return deletionNo
}

// Minimum Distances
// https://www.hackerrank.com/challenges/minimum-distances/problem
fun testMinimumDistances() {
    val result = minimumDistances(arrayOf(7, 1, 3, 4, 1, 7))
    println(result)
}
fun minimumDistances(a: Array<Int>): Int {
    val map = HashMap<Int, Int>()
    var minValue = Int.MAX_VALUE

    a.forEachIndexed { index, value ->
        val isExist = map[value]
        if (isExist != null) {
            val diff = abs(isExist - index)
            if (diff < minValue) minValue = diff
        } else {
            map[value] = index
        }
    }

    return if (minValue == Int.MAX_VALUE) -1 else minValue
}

// Queen's Attack II
// https://www.hackerrank.com/challenges/queens-attack-2/problem
fun testQueensAttack() {
    val no = queensAttack(
        4,
        0,
        4,
        4,
        arrayOf()
    )
    println(no)
}
fun queensAttack(
    rowColumnNo: Int,
    obstaclesNo: Int,
    queenRow: Int,
    queenColumn: Int,
    obstacles: Array<Array<Int>>
): Int {
    val queenPosition = queenRow to queenColumn
    val obstaclesSet = HashSet<Pair<Int, Int>>()
    obstacles.forEach {
        obstaclesSet.add(Pair(it[0], it[1]))
    }
    return getPathsCount(rowColumnNo, obstaclesSet, queenPosition)
}
fun getPathsCount(
    rowColumnNo: Int,
    obstacles: HashSet<Pair<Int, Int>>,
    queenPosition: Pair<Int, Int>
): Int {
    var pathsCount = 0
    for (row in (-1..1)) {
        for (column in (-1..1)) {
            pathsCount += getPathCount(
                rowColumnNo,
                obstacles,
                row,
                column,
                queenPosition.copy(
                    queenPosition.first + row,
                    queenPosition.second + column
                )
            )
        }
    }
    return pathsCount
}
fun getPathCount(
    rowColumnNo: Int,
    obstacles: HashSet<Pair<Int, Int>>,
    incrementRow: Int,
    incrementColumn: Int,
    cellPosition: Pair<Int, Int>
): Int {
    if (incrementRow == 0 && incrementColumn == 0) return 0
    if (isObstacle(obstacles, cellPosition.first, cellPosition.second)) return 0
    if (!isInBound(rowColumnNo, cellPosition.first, cellPosition.second)) return 0
    if (isLastCell(rowColumnNo, cellPosition.first, cellPosition.second, incrementRow, incrementColumn)) return 1

    val nextCell = cellPosition.copy(
        cellPosition.first + incrementRow,
        cellPosition.second + incrementColumn
    )
    return 1 + getPathCount(
        rowColumnNo,
        obstacles,
        incrementRow,
        incrementColumn,
        nextCell
    )
}
fun isInBound(
    rowColumnNo: Int,
    cellRow: Int,
    cellColumn: Int
): Boolean {
    return cellRow in 1..rowColumnNo && cellColumn in 1..rowColumnNo
}
fun isLastCell(
    rowColumnNo: Int,
    cellRow: Int,
    cellColumn: Int,
    incrementRow: Int,
    incrementColumn: Int
): Boolean {
    val row = cellRow + incrementRow
    val column = cellColumn + incrementColumn
    return !isInBound(rowColumnNo, row, column)
}
fun isObstacle(
    obstacles: HashSet<Pair<Int, Int>>,
    cellRow: Int,
    cellColumn: Int
): Boolean {
    return obstacles.contains(Pair(cellRow, cellColumn))
}

// Non-Divisible Subset
// https://www.hackerrank.com/challenges/non-divisible-subset/problem
fun testNonDivisibleSubset() {
//    val result = nonDivisibleSubset(
//        9,
//        arrayOf(
//            61197933,
//            56459859,
//            319018589,
//            271720536,
//            358582070,
//            849720202,
//            481165658,
//            675266245,
//            541667092,
//            615618805,
//            129027583,
//            755570852,
//            437001718,
//            86763458,
//            791564527,
//            163795318,
//            981341013,
//            516958303,
//            592324531,
//            611671866,
//            157795445,
//            718701842,
//            773810960,
//            72800260,
//            281252802,
//            404319361,
//            757224413,
//            682600363,
//            606641861,
//            986674925,
//            176725535,
//            256166138,
//            827035972,
//            124896145,
//            37969090,
//            136814243,
//            274957936,
//            980688849,
//            293456190,
//            141209943,
//            346065260,
//            550594766,
//            132159011,
//            491368651,
//            3772767,
//            131852400,
//            633124868,
//            148168785,
//            339205816,
//            705527969,
//            551343090,
//            824338597,
//            241776176,
//            286091680,
//            919941899,
//            728704934,
//            37548669,
//            513249437,
//            888944501,
//            239457900,
//            977532594,
//            140391002,
//            260004333,
//            911069927,
//            586821751,
//            113740158,
//            370372870,
//            97014913,
//            28011421,
//            489017248,
//            492953261,
//            73530695,
//            27277034,
//            570013262,
//            81306939,
//            519086053,
//            993680429,
//            599609256,
//            639477062,
//            677313848,
//            950497430,
//            672417749,
//            266140123,
//            601572332,
//            273157042,
//            777834449,
//            123586826
//        )
//    )

    val result = nonDivisibleSubset(4, arrayOf(5, 9, 13, 17))
    println(result)
}
fun nonDivisibleSubset(divisor: Int, numbers: Array<Int>): Int {
    val allReminders = IntArray(divisor)
    numbers.forEach { number ->
        val index = number % divisor
        allReminders[index]++
    }

    val zeroRem = allReminders[0]
    allReminders[0] = min(zeroRem, 1)

    var result = 0
    result += allReminders[0]

    if (divisor % 2 == 0) {
        val midPointValue = allReminders[divisor / 2]
        result += min(midPointValue, 1)
    }

    for (number in 1..(allReminders.lastIndex / 2)) {
        val counterPart = divisor - number
        val maxValue = max(allReminders[number], allReminders[counterPart])
        result += maxValue
    }

    println(allReminders.joinToString())

    return result
}
fun allSubSets(originalSet: List<Int>): Set<Set<Int>> {
    val sets = mutableSetOf<Set<Int>>()
    if (originalSet.isEmpty()) {
        sets.add(emptySet())
        return sets
    }
    val head = originalSet[0]
    val rest = originalSet.subList(1, originalSet.size)
    allSubSets(rest).forEach { set ->
        val newSet = HashSet<Int>()
        newSet.add(head)
        newSet.addAll(set)
        sets.add(newSet)
        sets.add(set)
    }
    return sets
}

// The Time in Words
// https://www.hackerrank.com/challenges/the-time-in-words/problem
fun testTimeInWords() {
    val result = timeInWords(7, 0)
    println(result)
}
fun timeInWords(h: Int, m: Int): String {
    return when(m) {
        0 -> "${h.toNumberString()} o' clock"
        in 0..30 -> {
            val minuteString = if (m == 15) {
                "quarter"
            } else if (m == 30) {
                "half"
            } else {
                val minuteString = if (m == 1 ) "minute" else "minutes"
                "${m.toNumberString()} $minuteString"
            }

            "$minuteString past ${h.toNumberString()}"
        }
        else -> {
            val remainingMinutes = (60 - m)
            val minuteString = if (remainingMinutes == 15) {
                "quarter"
            } else {
                val minuteString = if (remainingMinutes == 1 ) "minute" else "minutes"
                "${remainingMinutes.toNumberString()} $minuteString"
            }
            "$minuteString to ${(h + 1).toNumberString()}"
        }
    }
}
fun Int.toNumberString(): String {
    return when(this) {
        1 -> "one"
        2 -> "two"
        3 -> "three"
        4 -> "four"
        5 -> "five"
        6 -> "six"
        7 -> "seven"
        8 -> "eight"
        9 -> "nine"
        10 -> "ten"
        11 -> "eleven"
        12 -> "twelve"
        13 -> "thirteen"
        14 -> "fourteen"
        15 -> "fifteen"
        16 -> "sixteen"
        17 -> "seventeen"
        18 -> "eighteen"
        19 -> "nineteen"
        20 -> "twenty"
        21 -> "twenty one"
        22 -> "twenty two"
        23 -> "twenty three"
        24 -> "twenty four"
        25 -> "twenty five"
        26 -> "twenty six"
        27 -> "twenty seven"
        28 -> "twenty eight"
        29 -> "twenty nine"
        else -> "thirty"
    }
}