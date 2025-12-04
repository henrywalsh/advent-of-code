package year2025.day4

import kotlin.time.measureTime

fun main() {
    val filePath = "src/year2025/day4/input/exampleInput.txt"

    var answerPartOne = 0
    val timePartOne = measureTime {
        val forkliftHelperPartOne = ForkliftHelperPartOne()
        answerPartOne = forkliftHelperPartOne.getMoveableRolls(filePath)
    }
    println("partOne: answer $answerPartOne, time $timePartOne")

    var answerPartTwo = 0
    val timePartTwo = measureTime {
        val forkliftHelperPartTwo = ForkliftHelperPartTwo()
        answerPartTwo = forkliftHelperPartTwo.getMoveableRolls(filePath)
    }
    println("partTwo: answer $answerPartTwo, time $timePartTwo")
}