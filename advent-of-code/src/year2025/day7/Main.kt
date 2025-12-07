package year2025.day7

import kotlin.time.measureTime

fun main() {
    val filePath = "src/year2025/day7/input/puzzleInput.txt"
    
    var answerPartOne: Int = 0
    val timePartOne = measureTime {
        val tachyonAnalyzer = TachyonAnalyzer()
        answerPartOne = tachyonAnalyzer.partOne(filePath)
    }
    println("partOne: answer $answerPartOne, time $timePartOne")
    
    var answerPartTwo: Long = 0
    val timePartTwo = measureTime {
        val tachyonAnalyzer = TachyonAnalyzer()
        answerPartTwo = tachyonAnalyzer.partTwo(filePath)
    }
    println("partTwo: answer $answerPartTwo, time $timePartTwo")
}