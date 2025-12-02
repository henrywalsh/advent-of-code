package year2025.day2

import java.io.File
import java.io.InputStream
import kotlin.math.log10

class InvalidProductFinderPartTwoFaster {
    val tens = arrayOf(
        1,
        10,
        100,
        1000,
        10000,
        100000,
        1000000,
        10000000,
        100000000,
        1000000000,
        100000000000,
        1000000000000,
        10000000000000,
        100000000000000,
        1000000000000000,
        10000000000000000,
        100000000000000000,
        1000000000000000000,
    )

    fun findSumOfInvalidIds(filePath: String): Long {
        val ranges = readInput(filePath)

        return sumInvalidRanges(ranges)
    }

    private fun readInput(filePath: String): List<Range> {
        val inputStream: InputStream = File(filePath).inputStream()
        val ranges = mutableListOf<Range>()


        inputStream.bufferedReader().forEachLine {
            val values = it.split(",")

            for (value in values) {
                val floor = value.substringBefore("-").toLong()
                val ceiling = value.substringAfter("-").toLong()

                ranges.add(Range(floor, ceiling))
            }
        }

        return ranges
    }

    private fun sumInvalidRanges(ranges: List<Range>): Long {
        var sum: Long = 0

        for (range in ranges) {
            sum += sumInvalidInRange(range)
        }

        return sum
    }

    private fun sumInvalidInRange(range: Range): Long {
        var sum: Long = 0
        for (i in range.floor..range.ceiling) {
            if (isRepeated(i)) {
                sum += i
            }
        }

        return sum
    }

    private fun isRepeated(num: Long): Boolean {
        val digits = log10(num.toDouble()).toInt() + 1

        for (chunkDigits in 1..(digits / 2)) {
            if (digits % chunkDigits != 0) {
                continue
            }

            if (isChunkRepeated(num, chunkDigits)) {
                return true
            }
        }

        return false
    }

    private fun isChunkRepeated(num: Long, chunkDigits: Int): Boolean {
        val repeated = num % tens[chunkDigits]

        var current = num
        while(current > 0) {
            if (current % tens[chunkDigits] != repeated) {
                return false
            }

            current /= tens[chunkDigits]
        }

        return true
    }
}