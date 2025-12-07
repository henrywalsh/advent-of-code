package year2025.day7

import java.io.File
import java.io.InputStream

class TachyonAnalyzer {
    fun partOne(filePath: String): Int {
        var activeBeams = mutableSetOf<Int>()
        var splits = 0
        
        val inputStream: InputStream = File(filePath).inputStream()
        inputStream.bufferedReader().forEachLine {
            val newBeams = mutableSetOf<Int>()
            for (beam in activeBeams) {
                if (it[beam] == '^') {
                    newBeams.add(beam - 1)
                    newBeams.add(beam + 1)
                    splits++
                } else {
                    newBeams.add(beam)
                }
            }
            
            activeBeams = newBeams
            
            if (activeBeams.isEmpty()) {
                for (i in 0..<it.length) {
                    if (it[i] == 'S') {
                        activeBeams.add(i)
                    }
                }
            }
        }
        
        return splits
    }

    fun partTwo(filePath: String): Long {
        var activeBeams = mutableListOf<Long>()
        var realities: Long = 0

        val inputStream: InputStream = File(filePath).inputStream()
        inputStream.bufferedReader().forEachLine {
            if (activeBeams.isEmpty()) {
                activeBeams = MutableList(it.length) { 0 }
                for (i in 0..<it.length) {
                    if (it[i] == 'S') {
                        activeBeams[i]++
                    }
                }
            } else {
                val newBeams = MutableList<Long>(it.length) { 0 }
                for (i in 0..<it.length) {
                    if (activeBeams[i] > 0) {
                        if (it[i] == '^') {
                            newBeams[i - 1] += activeBeams[i]
                            newBeams[i + 1] += activeBeams[i]
                        } else {
                            newBeams[i] += activeBeams[i]
                        }
                    }
                }

                activeBeams = newBeams
            }
        }
        
        for (beam in activeBeams) {
            realities += beam
        }

        return realities
    }
}