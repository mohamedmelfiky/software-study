package practice

import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.measureTimeMillis

fun main() {
//    testReverseArray()
//    testDynamicArray()
//    testRotateLeft()
//    testIsBalanced()
}

fun lerp(start: Int, stop: Int, fraction: Float): Float {
    (stop - start) * fraction + start
//    ((stop * fraction) - (start * fraction)) + start
//    (start - (start * fraction)) + (fraction * stop)
//    50x - 10x + 10
//    50x - 10(x - 1) -> 50x + 10(-x + 1) -> 50x + 10(1 - x) -> 10(1 - x) + 50x
//    -10x + 10 -> -10(x-1) -> 10(-x + 1) -> 10(1 - x)
//    10(1 - x) + 50x
    return (1 - fraction) * start + fraction * stop
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