package me.falsehonesty.utils

import kotlin.math.abs

typealias Coord = Pair<Int, Int>

class XY(private var x: Int, private var y: Int) {
    fun off(x: Int = 0, y: Int = 0) = apply {
        this.x += x
        this.y += y
    }

    fun off(dir: Direction) = apply {
        off(dir.dx, dir.dy)
    }

    fun dist(coord: Coord) = dist(coord.first, coord.second)

    fun dist(xy: XY) = dist(xy.x, xy.y)

    fun dist(x: Int = 0, y: Int = 0): Int {
        return abs(this.x - x) + abs(this.y - y)
    }

    fun toCoord() = x to y

    fun component1() = x
    fun component2() = y

    override fun equals(other: Any?): Boolean {
        return other is XY && other.x == x && other.y == y
    }

    override fun toString(): String {
        return "($x, $y)"
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}

infix fun Int.xy(other: Int): XY {
    return XY(this, other)
}

fun Int.exceeds(max: Int, min: Int = 0): Boolean {
    return this < min || this > max
}

fun Coord.exceeds(max: Int, min: Int = 0): Boolean {
    return first.exceeds(max, min) || second.exceeds(max, min)
}
