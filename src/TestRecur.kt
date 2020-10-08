import kotlin.math.floor
import kotlin.math.pow

fun main() {
//    val m = 16
//    val key = 12354
//    val a = 0.5451
//    val hash = floor(m * ((key * a) % 1)).toInt()

    val r = 5
    val capacity = 2.0.pow(r).toInt()
    val word = 32
    val A = 289702021
    val key = 12354
    val result = A.toLong() * key
    val hash2 = (result % 2.0.pow(word).toInt()) shr (word - r)
}