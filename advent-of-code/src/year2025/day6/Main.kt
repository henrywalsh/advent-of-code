package year2025.day6

import kotlin.time.measureTime

fun main() {
    val filePath = "src/year2025/day6/input/puzzleInput.txt"

    var answerPartOne: Long = 0
    val timePartOne = measureTime {
        val cephalopodHomeworkHelper = CephalopodHomeworkHelper()
        answerPartOne = cephalopodHomeworkHelper.partOne(filePath)
    }
    println("partOne: answer $answerPartOne, time $timePartOne")
    
    var answerPartTwo: Long = 0
    val timePartTwo = measureTime {
        val cephalopodHomeworkHelper = CephalopodHomeworkHelper()
        answerPartTwo = cephalopodHomeworkHelper.partTwo(filePath)
    }
    println("partTwo: answer $answerPartTwo, time $timePartTwo")
}