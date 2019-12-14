package me.falsehonesty.day1

import me.falsehonesty.utils.resource

fun main() {
    val input = resource("day1in.txt")

    val sum = input.map { it.toInt() / 3 - 2 }.sum()

    println(sum)
}
