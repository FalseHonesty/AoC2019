package me.falsehonesty.day1

import me.falsehonesty.utils.resource
import kotlin.math.max

fun main() {
    val input = resource("/day1in.txt")

    val weights = input.map { it.toInt() }

    var sum = 0
    for (weight in weights) {
        var added = weight
        do {
            added = added / 3 - 2
            sum += max(added, 0)
        } while (added > 0)
    }

    println(sum)
}
