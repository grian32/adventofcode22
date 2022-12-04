package days

import Day
import hasAll
import hasAny
import java.io.File

class Day4 : Day<Int, List<Pair<IntRange, IntRange>>> {
    override val input = File("src/main/resources/day4.txt").readText()

    override fun processInput(): List<Pair<IntRange, IntRange>> = input.lines().map {
        val split = it.split(",")
        val firstRange = split[0].split("-").map(String::toInt)
        val secondRange = split[1].split("-").map(String::toInt)

        IntRange(firstRange[0], firstRange[1]) to IntRange(secondRange[0], secondRange[1])
    }

    override fun part1(): Int {
        val processedInput = processInput()
        var fullyOverlapped = 0

        for (i in processedInput) {
            if (i.first.hasAll(i.second) || i.second.hasAll(i.first)) fullyOverlapped++
        }

        return fullyOverlapped
    }

    override fun part2(): Int {
        val processedInput = processInput()
        var anyOverlapped = 0

        for (i in processedInput) {
            if (i.first.hasAny(i.second) || i.second.hasAny(i.first)) anyOverlapped++
        }

        return anyOverlapped
    }
}