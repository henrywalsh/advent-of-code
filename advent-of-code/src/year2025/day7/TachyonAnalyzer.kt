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
        var beams = Array<Long>(0) { 0 }
        var realities: Long = 0

        val inputStream: InputStream = File(filePath).inputStream()
        inputStream.bufferedReader().forEachLine {
            if (beams.isEmpty()) {
                beams = Array(it.length) { 0 }
                for (i in 0..<it.length) {
                    if (it[i] == 'S') {
                        beams[i]++
                    }
                }
            } else {
                for (i in 0..<it.length) {
                    if (beams[i] > 0) {
                        if (it[i] == '^') {
                            beams[i - 1] += beams[i]
                            beams[i + 1] += beams[i]
                            beams[i] = 0
                        }
                    }
                }
            }
        }
        
        for (beam in beams) {
            realities += beam
        }

        return realities
    }
}