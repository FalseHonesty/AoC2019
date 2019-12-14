package me.falsehonesty.day3

import me.falsehonesty.utils.Coord
import me.falsehonesty.utils.DirectionAndMagnitude
import me.falsehonesty.utils.XY
import me.falsehonesty.utils.day

fun main() {
    val moves = day(3)

    val (wire1, wire2) = moves.map { it.split(",").map { desc -> DirectionAndMagnitude.from(desc) } }

    val walked = mutableMapOf<Coord, Int>()
    var pos = XY(0, 0)
    var steps = 0
    for (move in wire1) {
        val (dir, mag) = move

        repeat(mag) {
            steps++
            pos.off(dir)
            walked[pos.toCoord()] = steps
        }
    }

    println("Done with wire1")

    var closestCross = Int.MAX_VALUE
    pos = XY(0, 0)
    steps = 0
    for (move in wire2) {
        val (dir, mag) = move

        repeat(mag) {
            steps++
            pos.off(dir)

            val cross = walked[pos.toCoord()]
            if (cross != null) {
                val totalSteps = steps + cross
                if (totalSteps < closestCross) {
                    closestCross = totalSteps
                }
            }
        }
    }

    println("Closest: $closestCross")
}