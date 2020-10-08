package hashTable

private const val DefaultSize = 16

class HashTable<K, V> {

    private sealed class HashTableEntry<out K, out V> {
        data class Entry<out K, out V>(val key: K, val value: V) : HashTableEntry<K, V>()
        object Deleted : HashTableEntry<Nothing, Nothing>() {
            override fun toString(): String {
                return "Deleted"
            }
        }
    }

    private var capacity = DefaultSize
    private val threshold = 0.75f
    private var array = Array<HashTableEntry<K, V>?>(capacity) { null }
    var size = 0
        private set

    // if key already exists, update value
    fun add(key: K, value: V): V {
        if (needRehash()) rehash(capacity * 2)
        return addEntry(HashTableEntry.Entry(key, value))
    }

    private fun addEntry(entry: HashTableEntry.Entry<K, V>): V {
        val index = getInsertIndex(entry.key)
        val currentValue = array[index]
        if (currentValue !is HashTableEntry.Entry) size++
        array[index] = entry
        return entry.value
    }

    fun exists(key: K): Boolean {
        return get(key) != null
    }

    fun get(key: K): V? {
        val index = findIndex(key)
        val currentEntry = array[index]
        if (currentEntry !is HashTableEntry.Entry) return null
        return currentEntry.value
    }

    fun remove(key: K): Boolean {
        val index = findIndex(key)
        val currentEntry = array[index]
        if (currentEntry !is HashTableEntry.Entry) return false
        array[index] = HashTableEntry.Deleted
        size--
        return true
    }

    private fun hash(key: K, size: Int): Int {
        val hashCode = key.hashCode()
        val hashCodePositive = hashCode and 0x7F_FF_FF_FF
        return hashCodePositive % size
    }

    private fun getInsertIndex(key: K): Int {
        var index = hash(key, array.size)
        var currentEntry = array[index]
        while (currentEntry is HashTableEntry.Entry) {
            if (currentEntry.key == key) break
            index = (index + 1) % array.size
            currentEntry = array[index]
        }
        return index
    }

    private fun findIndex(key: K): Int {
        var index = hash(key, array.size)
        var currentEntry = array[index]
        while (currentEntry != null) {
            if (currentEntry is HashTableEntry.Entry && currentEntry.key == key) break
            index = (index + 1) % array.size
            currentEntry = array[index]
        }
        return index
    }

    private fun needRehash(): Boolean {
        return size / array.size.toFloat() == threshold
    }

    private fun rehash(newCapacity: Int) {
        val currentArray = array
        capacity = newCapacity
        array = Array(newCapacity) { null }
        currentArray.forEach { entry ->
            if (entry is HashTableEntry.Entry) addEntry(entry)
        }
    }

    fun debug() {
        println(array.joinToString())
    }

}