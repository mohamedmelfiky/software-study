package bitwise

import kotlin.experimental.inv

fun andOperator() {
    //              7654_3210      10
    val number1 = 0b1001_0110 // 0x96
    val number2 = 0b0011_0010 // 0x32
    val answer  = 0b0001_0010 // 0x12

    println(number1 and number2)
    println(answer)
}

fun orOperator() {
    val number1 = 0b1001_0110 // 0x96
    val number2 = 0b0011_0010 // 0x32
    val answer  = 0b1011_0110 // 0xB6

    println(number1 or number2)
    println(answer)
}

fun xorOperator() {
    // Performing XOR on a value against itself always yields zero
    val number1 = 0b1001_0110 // 0x96
    val number2 = 0b0011_0010 // 0x32
    val answer  = 0b1010_0100 // 0xA4

    println(number1 xor number2)
    println(answer)
}

fun notOperator() {
    // unary operation
    val number1: Short = 0b0000_0000_1001_0110  // 0x0096
    val answer: UShort = 0b1111_1111_0110_1001u // 0xFF69
    val answer2: Int = -number1 - 1 // 0xFF69 because we use two's complement (first complement + 1)

    println(number1.inv())
    println(answer.toShort())
    println(answer2)
}

fun leftShiftOperator() {
    val number1  = 0b1001_0110  // 0x96
    val position = 0b0000_0010  // 0x02
    val answer   = 0b0010_0101_1000  // 0x258

    println(number1 shl position)
    println(answer)
}

fun rightShiftOperator() {
    // filling the leftmost bits with copies of the sign bit
    val number1  = 0b1001_0110  // 0x96
    val position = 0b0000_0010  // 0x18
    val answer   = 0b0010_0101  // 0x25

    println(number1 shr position)
    println(answer)
}

fun unsignedRightShiftOperator() {
    //  filling the leftmost bits with zeros
    val number1  = 0b1001_0110  // 0x96
    val position = 0b0000_0010  // 0x02
    val answer   = 0b0010_0101  // 0x25

    println(number1 ushr position)
    println(answer)
}

fun setBit(numberBinary: Int, position: Int): Int {
    val mask = 1 shl position
    return numberBinary or mask
}

fun clearBit(numberBinary: Int, position: Int): Int {
    val mask = 1 shl position
    return numberBinary and mask.inv()
}

fun flipBit(numberBinary: Int, position: Int): Int {
    val mask = 1 shl position
    return numberBinary xor mask
}

fun isBitSet(numberBinary: Int, position: Int): Boolean {
    val numberBinaryShifted = numberBinary shr position
    return numberBinaryShifted and 1 == 1
}

fun isBitSet2(numberBinary: Int, position: Int): Boolean {
    val mask = 1 shl position
    return numberBinary and mask != 0
}

//fun modifyBit(numberBinary: Int, position: Int, set: Boolean): Int {
//    val numberBinaryShifted = numberBinary shr position
//    return numberBinaryShifted and 1 == 1
//}

fun reverseString(value: CharArray): String {
    var start = 0
    var end = value.lastIndex

    while (start < end) {
        value[start] = (value[start].toInt() xor value[end].toInt()).toChar()
        value[end] = (value[end].toInt() xor value[start].toInt()).toChar()
        value[start] = (value[start].toInt() xor value[end].toInt()).toChar()

        ++start
        --end
    }

    return String(value)
}

fun main() {
//    andOperator()
//    orOperator()
//    xorOperator()
//    notOperator()
//
//    // shifts used for bit mask
//    leftShiftOperator()
//    rightShiftOperator()
//    unsignedRightShiftOperator()
//
//    // basic bit manipulation
//    // int does not use signed bit it uses 2's complement
//    val number = 0b0000_1000
//    val setBitResult = setBit(number, 0)
//    println(setBitResult)
//
//    val clearBitResult = clearBit(number, 3)
//    println(clearBitResult)
//
//    val flipBitResult = flipBit(number, 0)
//    println(flipBitResult)
//
//    val isBitSetResult = isBitSet(number, 3)
//    val isBitSet2Result = isBitSet(number, 3)
//    println(isBitSetResult)
//    println(isBitSet2Result)
//
//    // Bit tricks
//    // check even
//    // check is a number is power of two (x and x-1) == 0
//    // count number of bits that are different between two numbers
//
//    val word = "hello".toCharArray()
//    println(word)
//    reverseString(word)
//    println(word)
}