package year2025.day8

import kotlin.time.measureTime

fun main() {
    val filePath = "src/year2025/day8/input/puzzleInput.txt"
    
    var answerPartOne: Int = 0
    val timePartOne = measureTime {
        val junctionBoxConnector = JunctionBoxConnector()
        answerPartOne = junctionBoxConnector.partOne(filePath, 1000, 3)
    }
    println("partOne: answer $answerPartOne, time $timePartOne")

    var answerPartTwo: Long = 0
    val timePartTwo = measureTime {
        val junctionBoxConnector = JunctionBoxConnector()
        answerPartTwo = junctionBoxConnector.partTwo(filePath)
    }
    println("partTwo: answer $answerPartTwo, time $timePartTwo")
}