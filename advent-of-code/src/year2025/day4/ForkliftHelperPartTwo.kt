package year2025.day4

import java.io.File
import java.io.InputStream

class ForkliftHelperPartTwo {
    var rollsOfPaper: List<String> = mutableListOf()

    fun getMoveableRolls(filePath: String): Int {
        this.rollsOfPaper = getInput(filePath)

        return numMoveableRolls()
    }

    private fun getInput(filePath: String): List<String> {
        val inputStream: InputStream = File(filePath).inputStream()
        val rollsOfPaper = mutableListOf<String>()

        inputStream.bufferedReader().forEachLine {
            rollsOfPaper.add(it)
        }

        return rollsOfPaper
    }

    private fun numMoveableRolls(): Int {
        var rolls = 0

        var shouldCheck = true
        while (shouldCheck) {
            val rollsInRound = numMoveableRollsInRound(this.rollsOfPaper)

            if (rollsInRound == 0) {
                shouldCheck = false
            }

            rolls += rollsInRound
        }

        return rolls
    }

    private fun numMoveableRollsInRound(rollsOfPaper: List<String>): Int {
        val newRollsOfPaper = mutableListOf<String>()
        var rolls = 0

        for (row in 0..<rollsOfPaper.size) {
            val chars = StringBuilder()

            for (col in 0..<rollsOfPaper[row].length) {
                if (rollsOfPaper[row][col] == '@' && hasLessThanFourNeighbors(rollsOfPaper, row, col)) {
                    print("X")

                    chars.append('.')
                    rolls++
                } else {
                    print(rollsOfPaper[row][col])
                    chars.append(rollsOfPaper[row][col])
                }
            }

            println()
            newRollsOfPaper.add(chars.toString())
        }

        println()
        this.rollsOfPaper = newRollsOfPaper

        return rolls
    }

    private fun hasLessThanFourNeighbors(rollsOfPaper: List<String>, row: Int, col: Int): Boolean {
        var neighbors = 0

        if (row > 0) {
            if (col > 0) {
                if (rollsOfPaper[row-1][col-1] == '@') {
                    neighbors++
                }
            }

            if (rollsOfPaper[row-1][col] == '@') {
                neighbors++
            }

            if (col < rollsOfPaper[row].length - 1) {
                if (rollsOfPaper[row-1][col+1] == '@') {
                    neighbors++
                }
            }
        }

        if (col > 0) {
            if (rollsOfPaper[row][col-1] == '@') {
                neighbors++
            }
        }

        if (col < rollsOfPaper[row].length - 1) {
            if (rollsOfPaper[row][col+1] == '@') {
                neighbors++
            }
        }

        if (row < rollsOfPaper.size - 1) {
            if (col > 0) {
                if (rollsOfPaper[row+1][col-1] == '@') {
                    neighbors++
                }
            }

            if (rollsOfPaper[row+1][col] == '@') {
                neighbors++
            }

            if (col < rollsOfPaper[row].length - 1) {
                if (rollsOfPaper[row+1][col+1] == '@') {
                    neighbors++
                }
            }
        }

        return neighbors < 4
    }
}