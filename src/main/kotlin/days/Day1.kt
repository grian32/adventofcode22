package days

import Day
import java.io.File

class Day1 : Day<Int, List<Int>> {
    override val input = File("src/main/resources/day1.txt").readText()

    override fun processInput(): List<Int> {
        val splitInput = input.split("\n\n")

        return splitInput.map {
            it.lines().map(String::toInt).sum()
        }
    }

    override fun part1(): Int = processInput().maxOrNull()!!


    override fun part2(): Int = processInput().sortedDescending().subList(0, 3).sum()

}