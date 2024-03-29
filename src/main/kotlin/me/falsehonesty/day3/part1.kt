package me.falsehonesty.day3

import me.falsehonesty.utils.Coord
import me.falsehonesty.utils.DirectionAndMagnitude
import me.falsehonesty.utils.XY
import me.falsehonesty.utils.day

fun main() {
    val moves = day(3)

    val (wire1, wire2) = moves.map { it.split(",").map { desc -> DirectionAndMagnitude.from(desc) } }

    val walked = mutableSetOf<Coord>()
    var pos = XY(0, 0)
    for (move in wire1) {
        val (dir, mag) = move

        repeat(mag) {
            pos.off(dir)
            walked.add(pos.toCoord())
        }
    }

    println("Done with wire1")

    var closestCross = Int.MAX_VALUE
    pos = XY(0, 0)
    for (move in wire2) {
        val (dir, mag) = move

        repeat(mag) {
            pos.off(dir)

            val asCoord = pos.toCoord()
            if (walked.contains(asCoord)) {
                val dist = pos.dist()
                if (dist < closestCross) {
                    closestCross = dist
                }
            }
        }
    }

    println("Closest: $closestCross")
}