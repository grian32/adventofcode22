package days

import Day
import java.io.File
import java.util.Stack

class Day5 : Day<String, Unit> {
    // TODO: parse the stacks properly cuz this aint it pal
    override val input: String = File("src/main/resources/day5.txt").readText()
    private val movements = mutableListOf<Movement>()
    override fun processInput() {
        val splitInput = input.split("\n\n")

        for (i in splitInput[1].split("\n")) {
            val values = i.replace("move ", "").replace("from ", "").replace("to ", "").split(" ")

            movements.add(Movement(values[0].toInt(), values[1].toInt() - 1, values[2].toInt() - 1))
        }
    }

    override fun part1(): String {
        processInput()

        val stacks = mutableListOf(
            mutableListOf('W', 'R', 'T', 'G'),
            mutableListOf('W', 'V', 'S', 'M', 'P', 'H', 'C', 'G'),
            mutableListOf('M', 'G', 'S', 'T', 'L', 'C'),
            mutableListOf('F', 'R', 'W', 'M', 'D', 'H', 'J'),
            mutableListOf('J', 'F', 'W', 'S', 'H', 'L', 'Q', 'P'),
            mutableListOf('S', 'M', 'F', 'N', 'D', 'J', 'P'),
            mutableListOf('J', 'S', 'C', 'G', 'F', 'D', 'B', 'Z'),
            mutableListOf('B', 'T', 'R'),
            mutableListOf('C', 'L', 'W', 'N', 'H')
        )

        repeat(movements.size) {
            val movement = movements[it]
            val from = stacks[movement.startPos]
            val to = stacks[movement.endPos]

            repeat(movement.amount) {
                to.add(0, from.removeFirst())
            }
        }

        return stacks.map { it[0] }.joinToString("")
    }

    override fun part2(): String {
        val stacks = mutableListOf(
            mutableListOf('W', 'R', 'T', 'G'),
            mutableListOf('W', 'V', 'S', 'M', 'P', 'H', 'C', 'G'),
            mutableListOf('M', 'G', 'S', 'T', 'L', 'C'),
            mutableListOf('F', 'R', 'W', 'M', 'D', 'H', 'J'),
            mutableListOf('J', 'F', 'W', 'S', 'H', 'L', 'Q', 'P'),
            mutableListOf('S', 'M', 'F', 'N', 'D', 'J', 'P'),
            mutableListOf('J', 'S', 'C', 'G', 'F', 'D', 'B', 'Z'),
            mutableListOf('B', 'T', 'R'),
            mutableListOf('C', 'L', 'W', 'N', 'H')
        )

        repeat(movements.size) {
            val movement = movements[it]
            val from = stacks[movement.startPos]
            val to = stacks[movement.endPos]

            to.addAll(0, from.take(movement.amount))

            repeat(movement.amount) { from.removeFirst() }
        }

        return stacks.map { it[0] }.joinToString("")
    }

    data class Movement(val amount: Int, val startPos: Int, val endPos: Int)
}