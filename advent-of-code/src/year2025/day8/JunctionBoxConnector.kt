package year2025.day8

import java.io.File
import java.io.InputStream
import java.util.PriorityQueue
import java.util.Stack
import kotlin.math.pow
import kotlin.math.sqrt

class JunctionBoxConnector {
    fun partOne(filePath: String, numConnections: Int, numCircuits: Int): Int {
        val junctionBoxes = readInput(filePath)

        val circuits = findCircuitsOrderedBySize(junctionBoxes, numConnections)

        var answer = 1
        for (i in 0..<numCircuits) {
            answer *= circuits[i]
        }

        return answer
    }

    private fun readInput(filePath: String): List<JunctionBox> {
        val junctionBoxes = mutableListOf<JunctionBox>()

        val inputStream: InputStream = File(filePath).inputStream()
        inputStream.bufferedReader().forEachLine {
            val coordinates = it.split(",")

            junctionBoxes.add(
                JunctionBox(
                    coordinates[0].toDouble(),
                    coordinates[1].toDouble(),
                    coordinates[2].toDouble()
                )
            )
        }

        return junctionBoxes
    }

    private fun findCircuitsOrderedBySize(junctionBoxes: List<JunctionBox>, numConnections: Int): List<Int> {
        val shortestConnections = PriorityQueue<Connection>{ c1, c2 ->
            c1.distance.compareTo(c2.distance)
        }

        for (i in 0..<junctionBoxes.size) {
            for (j in i+1..<junctionBoxes.size) {
                shortestConnections.add(
                    Connection(
                        i,
                        j,
                        getDistanceBetween(junctionBoxes[i], junctionBoxes[j])
                    )
                )
            }
        }

        val map = mutableMapOf<Int, MutableSet<Int>>()
        for (i in 0..<junctionBoxes.size) {
            map[i] = mutableSetOf()
        }

        for (i in 0..<numConnections) {
            val connection = shortestConnections.poll()

            // is this connection already in the same circuit?
            if (map[connection.a]!!.contains(connection.b)) {
                continue
            }

            map[connection.a]!!.add(connection.b)
            map[connection.b]!!.add(connection.a)

            val toVisit = mutableSetOf<Int>()

            toVisit.addAll(map[connection.a]!!)
            toVisit.addAll(map[connection.b]!!)

            val newSet = mutableSetOf<Int>()

            for (entry in toVisit) {
                newSet.addAll(map[entry]!!)
            }

            for (entry in toVisit) {
                map[entry]!!.addAll(newSet)
            }
        }

        for (entry in map) {
            entry.value.remove(entry.key)
        }

        val circuitSizes = mutableListOf<Int>()
        val visited = mutableSetOf<Int>()

        for (i in 0..<map.size) {
            if (visited.contains(i)) {
                continue
            }

            visited.add(i)
            var circuitSize = 1

            val toVisit = Stack<Int>()
            toVisit.addAll(map[i]!!)

            // kind of a depth first search?
            while (!toVisit.isEmpty()) {
                val current = toVisit.pop()

                if (visited.contains(current)) {
                    continue
                }

                circuitSize++

                visited.add(current)
                toVisit.addAll(map[current]!!)
            }

            circuitSizes.add(circuitSize)
        }

        return circuitSizes.sortedDescending()
    }

    private fun getDistanceBetween(a: JunctionBox, b: JunctionBox): Double {
        return sqrt((b.x - a.x).pow(2) + (b.y - a.y).pow(2) + (b.z - a.z).pow(2))
    }

    fun partTwo(filePath: String): Long {
        val junctionBoxes = readInput(filePath)

        val shortestConnections = PriorityQueue<Connection>{ c1, c2 ->
            c1.distance.compareTo(c2.distance)
        }

        for (i in 0..<junctionBoxes.size) {
            for (j in i+1..<junctionBoxes.size) {
                shortestConnections.add(
                    Connection(
                        i,
                        j,
                        getDistanceBetween(junctionBoxes[i], junctionBoxes[j])
                    )
                )
            }
        }

        val map = mutableMapOf<Int, MutableSet<Int>>()
        for (i in 0..<junctionBoxes.size) {
            map[i] = mutableSetOf()
        }

        while (!shortestConnections.isEmpty()) {
            val connection = shortestConnections.poll()

            // is this connection already in the same circuit?
            if (map[connection.a]!!.contains(connection.b)) {
                continue
            }

            map[connection.a]!!.add(connection.b)
            map[connection.b]!!.add(connection.a)

            val toVisit = mutableSetOf<Int>()

            toVisit.addAll(map[connection.a]!!)
            toVisit.addAll(map[connection.b]!!)

            val newSet = mutableSetOf<Int>()

            for (entry in toVisit) {
                newSet.addAll(map[entry]!!)
            }

            for (entry in toVisit) {
                map[entry]!!.addAll(newSet)
            }

            if (map[connection.a]!!.size == junctionBoxes.size) {
                return (junctionBoxes[connection.a].x * junctionBoxes[connection.b].x).toLong()
            }
        }

        return 0
    }
}