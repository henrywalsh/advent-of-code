package year2025.day3

import java.io.File
import java.io.InputStream

class JoltageCalculatorPartTwo {
    fun findJoltage(filePath: String): Long {
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

    private fun findJoltageForBanks(banks: List<Array<Int>>): Long {
        var joltage: Long = 0

        for (bank in banks) {
            joltage += findJoltageForBank(bank)
        }

        return joltage
    }

    private fun findJoltageForBank(bank: Array<Int>): Long {
        var joltage = getInitialJoltage(bank)

        for (i in bank.size - 1 - 12 downTo 0) {
           if (bank[i] >= joltage[0]) {
               joltage = adjustJoltage(joltage)
               joltage[0] = bank[i]
           }
        }

        return convertJoltageToValue(joltage)
    }

    private fun getInitialJoltage(bank: Array<Int>): Array<Int> {
        val initialJoltage: Array<Int> = Array(12) { 0 }

        for (i in 11 downTo 0) {
            initialJoltage[i] = bank[bank.size - 12 + i]
        }

        return initialJoltage
    }

    private fun adjustJoltage(joltage: Array<Int>): Array<Int> {
        var availableJoltage: Int = joltage[0]
        for (i in 1..<joltage.size) {
            if (availableJoltage >= joltage[i]) {
                val freeJoltage = joltage[i]

                joltage[i] = availableJoltage
                availableJoltage = freeJoltage
            } else {
                return joltage
            }
        }

        return joltage
    }

    private fun convertJoltageToValue(joltage: Array<Int>): Long {
        var value: Long = 0

        for (i in 0..<joltage.size) {
            value *= 10
            value += joltage[i]
        }

        return value
    }
}