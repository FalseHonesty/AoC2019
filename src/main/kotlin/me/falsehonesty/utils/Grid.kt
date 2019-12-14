package me.falsehonesty.utils

import java.lang.IllegalArgumentException

class Grid<T>(private val size: Int) {
    private var cursor = Coord(0, 0)
    private var content = listOf2D<Node<T>>(size, Node())

    fun getAtCursor(): T? {
        return content[cursor.first, cursor.second].value
    }

    operator fun get(i: Int, j: Int): T? {
        return content[i][j].value
    }

    fun moveCursor(dir: Direction, moveToBlanks: Boolean = false) {
        val oldCursor = cursor
        val newCursor = Coord(cursor.first + dir.dx, cursor.second + dir.dy)

        if (newCursor.exceeds(size - 1)) {
            return
        }

        if (moveToBlanks || getAtCursor() != null) {
            setCursor(newCursor)
        }
    }

    fun setContent(content: MutableList2D<Node<T>>) {
        this.content = content
    }

    fun setCursor(coord: Coord) = this.apply {
        cursor = coord
    }

    fun setCursor(x: Int, y: Int) = this.setCursor(Coord(x, y))

    class Node<T>(val value: T? = null)
}

enum class Direction(val dx: Int, val dy: Int) {
    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0);

    /*UP_RIGHT(1, -1),
    DOWN_RIGHT(1, 1),
    DOWN_LEFT(-1, 1),
    UP_LEFT(-1, -1);*/

    fun turnLeft(): Direction {
        return if (this.ordinal == 0) {
            Direction.values().last()
        } else {
            Direction.values()[this.ordinal - 1]
        }
    }

    fun turnRight(): Direction {
        return if (this.ordinal == Direction.values().size - 1) {
            Direction.values().first()
        } else {
            Direction.values()[this.ordinal + 1]
        }
    }

    companion object {
        fun fromLetter(letter: Char): Direction {
            return when(letter) {
                'L' -> LEFT
                'R' -> RIGHT
                'U' -> UP
                'D' -> DOWN
                else -> throw IllegalArgumentException("Not a valid Direction letter")
            }
        }
    }
}

data class DirectionAndMagnitude(val dir: Direction, val magnitude: Int) {
    companion object {
        /**
         * Parses formats like:
         * L99
         * U3
         */
        fun from(descriptor: String): DirectionAndMagnitude {
            val dir = Direction.fromLetter(descriptor[0])
            val magnitude = descriptor.substring(1).toInt()
            return DirectionAndMagnitude(dir, magnitude)
        }
    }
}