package me.falsehonesty.day2

import me.falsehonesty.utils.day

fun main() {
    val instructions = day(2).first().split(",").map { it.toInt() }.toIntArray()

    instructions[1] = 12
    instructions[2] = 2

    simulate(instructions)

    println("0x0: ${instructions[0]}")
}

fun simulate(memory: IntArray) {
    var pc = 0
    while (memory[pc] != 99) {
        val opcode = memory[pc]
        val arg0 = memory[memory[pc + 1]]
        val arg1 = memory[memory[pc + 2]]
        val arg2 = memory[pc + 3]

        val result = when (opcode) {
            1 -> arg0 + arg1
            2 -> arg0 * arg1
            else -> throw IllegalStateException("Unknown opcode ($opcode) at pc $pc")
        }

        memory[arg2] = result

        pc += 4
    }
}