interface Day<T, V> {
    val input: String

    fun part1(): T

    fun part2(): T

    fun processInput(): V
}