package me.falsehonesty.utils

typealias Coord = Pair<Int, Int>

data class XY(val x: Int, val y: Int) {
    fun off(x: Int = 0, y: Int = 0): XY {
        return XY(this.x + x, this.y + y)
    }

    fun off(dir: Direction): XY {
        return off(dir.dx, dir.dy)
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
