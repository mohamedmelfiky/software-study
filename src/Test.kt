import kotlin.math.absoluteValue
import kotlin.random.Random

class Test(
    val name: String,
    val age: Int,
    val idNo: Long
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Test

        if (name != other.name) return false
        if (age != other.age) return false
        if (idNo != other.idNo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + age
        result = 31 * result + idNo.hashCode()
        return result
    }
}

fun main() {
    val test1 = Test("Ramy", Random.nextInt(0, 60), Random.nextLong())
    val test2 = Test("Ragy",Random.nextInt(0, 60), Random.nextLong())
    val test3 = Test("asdasd", Random.nextInt(0, 60), Random.nextLong())
    val test4 = Test("gfdgfdg", Random.nextInt(0, 60), Random.nextLong())
    val test5 = Test("gfdgfdg", Random.nextInt(0, 60), Random.nextLong())
    val test6 = Test("gfdgfdg", Random.nextInt(0, 60), Random.nextLong())
    val test7 = Test("gfdgfdg", Random.nextInt(0, 60), Random.nextLong())

    val hashCode1 = test1.hashCode()
    val key1 = hashCode1 and 0x7fffffff

    val hashCode2 = test2.hashCode()
    val key2 = hashCode2 and 0x7fffffff

    val hashCode3 = test3.hashCode()
    val key3 = hashCode3 and 0x7fffffff

    val hashCode4 = test4.hashCode()
    val key4 = hashCode4 and 0x7fffffff

    val hashCode5 = test5.hashCode()
    val key5 = hashCode5 and 0x7fffffff

    val hashCode6 = test6.hashCode()
    val key6 = hashCode6 and 0x7fffffff

    val hashCode7 = test7.hashCode()
    val key7 = hashCode7 and 0x7fffffff

//    val index = positive % 7

    getIndex(key1)
    getIndex(key2)
    getIndex(key3)
    getIndex(key4)
    getIndex(key5)
    getIndex(key6)
    getIndex(key7)
}

private fun getIndex(key: Int) {
    val prime = 61
    val word = 32
    val size = 32
    val r = 5

    val K = key xor (key shr (word - r))
    val index = (prime * K) shr (word - r)

//    val index = (key.toUInt() * (1u)) shr (32 - 7)
//    val index = key % 10
    println("index: ${index.absoluteValue}")
}