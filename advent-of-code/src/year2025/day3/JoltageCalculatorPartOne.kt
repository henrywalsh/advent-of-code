package year2025.day3

import java.io.File
import java.io.InputStream

class JoltageCalculatorPartOne {
    fun findJoltage(filePath: String): Int {
        val banks = readBankInput(filePath)

        return findJoltageForBanks(banks)
    }

    private fun readBankInput(filePath: String): List<Array<Int>> {
        val inputStream: InputStream = File(filePath).inputStream()
        val banks = mutableListOf<Array<Int>>()


        inputStream.bufferedReader().forEachLine {
            banks.add(it.map(Character::getNumericValue).toTypedArray())
        }

        return banks
    }

    private fun findJoltageForBanks(banks: List<Array<Int>>): Int {
        var joltage: Int = 0

        for (bank in banks) {
            joltage += findJoltageForBank(bank)
        }

        return joltage
    }

    private fun findJoltageForBank(bank: Array<Int>): Int {
        var left: Int = 0
        var right: Int = bank.last()

        for (i in bank.size - 2 downTo 0) {
            if (bank[i] >= left) {
                if (left > right) {
                    right = left
                }

                left = bank[i]
            }
        }

        return (left * 10) + right
    }
}