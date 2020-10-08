package hashTable

import kotlin.math.floor
import kotlin.math.pow

fun main() {
    val table = HashTable<Int, Int>()
//    table.add(3, 3)
//    table.add(4, 4)
//    table.add(5, 5)
//    table.add(6, 6)
//    table.add(7, 7)
//    table.add(8, 8)
//    table.add(9, 9)
//    table.add(13, 13)
//    table.add(14, 14)
//    table.add(15, 15)
//    table.add(16, 16)
//    table.add(28, 28)
//    table.add(29, 29)
//    table.add(30, 30)

//    table.add(3, 3)
//    table.add(19, 19)
//    table.add(35, 35)
//    table.remove(19)
////    table.add(19, 19)
//    table.debug()
//    println(table.get(35))
//    table.remove(35)
//    println(table.get(35))
//    table.debug()
//    table.add(3, 3)
//    table.add(4, 4)
//    table.add(5, 5)
//    table.add(6, 6)
//    table.add(7, 7)
//    table.add(8, 8)
//    table.add(9, 9)
//    table.add(9, 9)
//    table.add(51, 51)
//    table.debug()
//    println(table.size)

//    val stringTable = HashTable<String, Int>()
//    stringTable.add("one", 1)
//    stringTable.add("two", 1)
//    stringTable.add("three", 1)
//    stringTable.add("four", 1)
//
//    stringTable.debug()
//    val key = 1245
//
//    val m = 16
//    val a = 0.1654f
//
//    val hash = floor(m * ((key * a) % 1)).toInt()
//    println(hash)

    val r = 4
    val size = 2.0.pow(r).toInt()
    val word = 32
    val A = 289702021
    val key = 1235
    val result = A.toLong() * key
    val hash2 = (result % 2.0.pow(word).toInt()) shr (word - r)
    println(hash2)
}