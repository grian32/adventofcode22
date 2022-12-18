package days

import Day
import java.io.File

class Day6 : Day<Int, Unit> {
    override val input: String = File("src/main/resources/day6.txt").readText()
    override fun processInput() { }

    override fun part1(): Int {
        for (i in input.indices) {
            val ahead = input.slice(i until i + 4)

            if (ahead.allUnique()) return i + 4
        }
        return 0
    }

    override fun part2(): Int {
        for (i in input.indices) {
            val ahead = input.slice(i until i + 14)

            if (ahead.allUnique()) return i + 14
        }
        return 0
    }

    private fun String.allUnique(): Boolean = this.toList() == this.toSet().toList()
}