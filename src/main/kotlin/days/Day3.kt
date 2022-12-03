package days

import Day
import java.io.File
import kotlin.math.floor

class Day3 : Day<Int, List<String>> {
    override val input = File("src/main/resources/day3.txt").readText()

    override fun processInput(): List<String> = input.lines()

    override fun part1(): Int {
        val processedInput = processInput().map {
            val half = floor(it.length.toDouble() / 2.0).toInt()
            val firstHalf = it.substring(0, half)
            val secondHalf = it.substring(half, it.length)

            Pair(firstHalf, secondHalf)
        }
        var prioritySum = 0

        // mad ugly
        for (i in processedInput) {
            for (j in i.first) {
                if (i.second.contains(j)) {
                    prioritySum += getPriority(j.toString())
                    break
                }
            }
        }

        return prioritySum
    }

    override fun part2(): Int {
        val processedInput = processInput().chunked(3)
        var prioritySum = 0

        for (i in processedInput) {
            for (j in i[0]) {
                if (i[1].contains(j) && i[2].contains(j)) {
                    prioritySum += getPriority(j.toString())
                    break
                }
            }
        }

        return prioritySum
    }

    private fun getPriority(letter: String): Int {
        val lowerCaseAlphabet = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")
        val upperCaseAlphabet = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")

        return if (letter.lowercase() == letter) {
            lowerCaseAlphabet.indexOf(letter) + 1 // priority is 1 indexed :sob:
        } else {
            upperCaseAlphabet.indexOf(letter) + 27 // upper case priority starts at 27 lol
        }
    }
}