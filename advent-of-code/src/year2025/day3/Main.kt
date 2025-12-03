package year2025.day3

import kotlin.time.measureTime

fun main() {
    val filePath = "src/year2025/day3/input/puzzleInput.txt"
    
    var answerPartOne: Int = 0
    val timePartOne = measureTime {
        val joltageCalculatorPartOne = JoltageCalculatorPartOne()
        answerPartOne = joltageCalculatorPartOne.findJoltage(filePath)
    }
    println("partOne: answer $answerPartOne, time $timePartOne")

    var answerPartTwo: Long = 0
    val timePartTwo = measureTime {
        val joltageCalculatorPartTwo = JoltageCalculatorPartTwo()
        answerPartTwo = joltageCalculatorPartTwo.findJoltage(filePath)
    }
    println("partTwo: answer $answerPartTwo, time $timePartTwo")
}