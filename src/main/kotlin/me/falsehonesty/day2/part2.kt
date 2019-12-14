package me.falsehonesty.day2

import me.falsehonesty.utils.day

const val SEARCH_VAL = 19690720

fun main() {
    val instructions = day(2).first().split(",").map { it.toInt() }

    for (noun in 0..99) {
        for (verb in 0..99) {
            val memory = instructions.toIntArray()

            memory[1] = noun
            memory[2] = verb

            simulate(memory)

            if (memory[0] == SEARCH_VAL) {
                println("Found: ${100 * noun + verb}")
                return
            }
        }
    }

}