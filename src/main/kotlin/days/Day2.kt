package days

import Day
import days.Day2.RPS.Companion.letterToRPS
import java.io.File

class Day2 : Day<Int, List<Pair<String, String>>>{
    override val input: String = File("src/main/resources/day2.txt").readText()

    override fun processInput(): List<Pair<String, String>> {
        val rawInput = input.lines().map {
            val split = it.split(" ")

            Pair(split[0], split[1])
        }

        return rawInput
    }

    override fun part1(): Int {
        val processedInput = processInput().map {
            Pair(
                RPS.letterToRPS(it.first),
                RPS.letterToRPS(it.second)
            )
        }
        var score = 0

        for (i in processedInput) {
            score += i.second.points + RPS.determineWinner(i.first, i.second).points
        }

        return score
    }

    override fun part2(): Int {
        val processedInput = processInput()
        var score = 0

        for (i in processedInput) {
            val theirChoice = RPS.letterToRPS(i.first)
            val requiredOutcome = Outcome.letterToOutcome(i.second)

            if (requiredOutcome == Outcome.Draw) {
                score += theirChoice.points + Outcome.Draw.points
                continue
            }

            if (requiredOutcome == Outcome.Win) {
                val myChoice = when (theirChoice) {
                    RPS.Paper -> RPS.Scissors
                    RPS.Rock -> RPS.Paper
                    RPS.Scissors -> RPS.Rock
                }

                score += myChoice.points + Outcome.Win.points
                continue
            }

            if (requiredOutcome == Outcome.Lose) {
                val myChoice = when (theirChoice) {
                    RPS.Scissors -> RPS.Paper
                    RPS.Paper -> RPS.Rock
                    RPS.Rock -> RPS.Scissors
                }

                score += myChoice.points + Outcome.Lose.points
                continue
            }
        }

        return score
    }

    enum class RPS(val theirSide: String, val yourSide: String, val points: Int) {
        Rock("A", "X", 1),
        Paper("B", "Y", 2),
        Scissors("C", "Z", 3);

        companion object {
            fun letterToRPS(letter: String): RPS {
                val map = mapOf(
                    "A" to Rock,
                    "X" to Rock,
                    "B" to Paper,
                    "Y" to Paper,
                    "C" to Scissors,
                    "Z" to Scissors,
                )

                return map[letter]!!
            }

            fun determineWinner(first: RPS, second: RPS): Outcome = when {
                first == second -> Outcome.Draw
                first == Paper && second == Scissors -> Outcome.Win
                first == Rock && second == Paper -> Outcome.Win
                first == Scissors && second == Rock -> Outcome.Win
                else -> Outcome.Lose
            }
        }
    }

    enum class Outcome(val points: Int) {
        Lose(0),
        Draw(3),
        Win(6);

        companion object {
            fun letterToOutcome(letter: String): Outcome {
                val map = mapOf(
                    "X" to Lose,
                    "Y" to Draw,
                    "Z" to Win
                )

                return map[letter]!!
            }
        }
    }
}