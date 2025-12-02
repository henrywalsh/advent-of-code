package year2025.day1

import kotlin.time.measureTime

fun main() {
    val filePath = "src/year2025/day1/input/puzzleInput.txt"
    val secretEntranceSolver = SecretEntranceSolver()

    var answerPartOne = 0
    val timePartOne = measureTime {
        answerPartOne = secretEntranceSolver.solvePartOne(filePath)
    }
    println(String.format("Part One: answer %d, time %s", answerPartOne, timePartOne.toString()))

    var answerPartTwo = 0
    val timePartTwo = measureTime {
        answerPartTwo = secretEntranceSolver.solvePartTwo(filePath)
    }
    println(String.format("Part Two: answer %d, time %s", answerPartTwo, timePartTwo.toString()))

}