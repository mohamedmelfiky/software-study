package practice

import java.util.*
import kotlin.collections.ArrayList
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
    if(root == null) return
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