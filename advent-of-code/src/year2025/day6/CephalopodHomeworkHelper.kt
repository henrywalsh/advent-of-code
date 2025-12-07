package year2025.day6

import java.io.File
import java.io.InputStream

class CephalopodHomeworkHelper {
    fun partOne(filePath: String): Long {
        val added = mutableListOf<Long>()
        val multiplied = mutableListOf<Long>()
        val operations = mutableListOf<Char>()

        val inputStream: InputStream = File(filePath).inputStream()
        inputStream.bufferedReader().forEachLine {
            val splitLine = it.split(" ")

            var i = 0
            for (entry in splitLine) {
                if (entry == "") {
                    continue
                }

                if (entry == "+") {
                    operations.add('+')
                } else if (entry == "*") {
                    operations.add('*')
                } else {
                    if (i < added.size) {
                        added[i] = added[i] + entry.toLong()
                        multiplied[i] = multiplied[i] * entry.toLong()
                    } else {
                        added.add(entry.toLong())
                        multiplied.add(entry.toLong())
                    }
                }

                i++
            }
        }

        var answer: Long = 0
        for (i in 0..<operations.size) {
            answer += if (operations[i] == '+') {
                added[i]
            } else {
                multiplied[i]
            }
        }

        return answer
    }

    fun partTwo(filePath: String): Long {
        var numbers = mutableListOf<Long>()
        val operations = mutableListOf<Char>()

        val inputStream: InputStream = File(filePath).inputStream()
        inputStream.bufferedReader().forEachLine {
            if (it.contains("+") || it.contains("*")) {
                for (operation in it) {
                    if (operation == ' ') {
                        continue
                    }

                    operations.add(operation)
                }
            } else {
                if (numbers.isEmpty()) {
                    numbers = MutableList(it.length) { 0 }
                }

                for (i in 0..<it.length) {
                    if (it[i] != ' ') {
                        numbers[i] *= 10
                        numbers[i] += it[i].digitToInt()
                    }
                }
            }
        }

        var total: Long = 0
        var i = 0
        for (operation in operations) {
            if (operation == '+') {
                var answer: Long = 0
                while (i < numbers.size && numbers[i] != 0L) {
                    answer += numbers[i]
                    i++
                }

                total += answer
            } else {
                var answer: Long = 1
                while (i < numbers.size && numbers[i] != 0L) {
                    answer *= numbers[i]
                    i++
                }

                total += answer
            }

            i++
        }

        return total
    }
}