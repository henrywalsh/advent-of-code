package year2025.day5

import kotlin.time.measureTime

fun main() {
    val filePath = "src/year2025/day5/input/puzzleInput.txt"

    var answerPartOne = 0
    val timePartOne = measureTime {
        val freshIngredientFinder = FreshIngredientFinder()
        answerPartOne = freshIngredientFinder.findFreshIngredients(filePath)
    }
    println("partOne: answer $answerPartOne, time $timePartOne")
    
    var answerPartTwo: Long = 0
    val timePartTwo = measureTime {
        val freshIngredientFinder = FreshIngredientFinder()
        answerPartTwo = freshIngredientFinder.findAmountOfFreshIngredientIds(filePath)
    }
    println("partTwo: answer $answerPartTwo, time $timePartTwo")
}