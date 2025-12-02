package year2025.day2

import kotlin.time.measureTime

fun main() {
    val filePath = "src/year2025/day2/input/puzzleInput.txt"

    var answerPartOne: Long = 0
    val timePartOne = measureTime {
        val invalidProductFinderPartOne = InvalidProductFinderPartOne()
        answerPartOne = invalidProductFinderPartOne.findSumOfInvalidIds(filePath)
    }
    println("partOne: answer $answerPartOne, time $timePartOne")

    var answerPartTwo: Long = 0
    val timePartTwo = measureTime {
        val invalidProductFinderPartTwo = InvalidProductFinderPartTwo()
        answerPartTwo = invalidProductFinderPartTwo.findSumOfInvalidIds(filePath)
    }
    println("partTwo: answer $answerPartTwo, time $timePartTwo")
}