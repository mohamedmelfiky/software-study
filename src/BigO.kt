fun main() {
//    val result = get_products_of_all_ints_except_at_index1(listOf(1, 7, 3, 4))
    val result = get_products_of_all_ints_except_at_index1(listOf(1, 0, 3, 4))
    println(result.toString())
}

fun get_products_of_all_ints_except_at_index1(list: List<Int>): List<Int> {
    val products = mutableListOf<Int>()
    for ((index, number) in list.withIndex()) {
        var product = 1
        for ((index1, number1) in list.withIndex()) {
            if (index != index1) {
                product *= number1
            }
        }
        products.add(product)
    }
    return products
}

fun get_products_of_all_ints_except_at_index2(list: List<Int>): List<Int> {
    val allProducts = list.reduce { acc, i -> acc * i }
    val products = mutableListOf<Int>()
    for ((index, number) in list.withIndex()) {
        var product = 1
        for ((index1, number1) in list.withIndex()) {
            if (index != index1) {
                product *= number1
            }
        }
        products.add(product)
    }
    return products
}