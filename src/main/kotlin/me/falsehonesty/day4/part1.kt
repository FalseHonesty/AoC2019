package me.falsehonesty.day4

import ar.com.agomez.choco.*
import org.chocosolver.solver.expression.discrete.arithmetic.ArExpression
import org.chocosolver.util.tools.MathUtils.pow

const val PW_LENGTH = 6
const val PW_MIN = 147981
const val PW_MAX = 691423

fun main() {
    model("# of Passwords") {
        // 0-9
        // 0, 10, 20, ..., 80, 90
        // 0, 100, 200, ..., 800, 900
        // ...
        // 0, 100_000, 200_000, ..., 800_000, 900_000
        val digs = (0 until PW_LENGTH).map { i ->
            val power = pow(10, i)
            intVar((0..9).map { it * power }.toIntArray())
        }.asReversed()

        // Go through each digit, divide by its respective power of 10
        // to reduce it to 0..9, then compare it with the following digit
        // to confirm it is lower.
        for (i in 0 until digs.lastIndex) {
            val leftPower = pow(10, digs.lastIndex - i)
            val rightPower = pow(10, digs.lastIndex - i - 1)
            ((digs[i] / leftPower) le (digs[i + 1] / rightPower)).post()
        }

        // Go through all of the digits, again dividing by its respective power of 10
        // to narrow it down to the correct 0..9 range, then make sure we have AT LEAST
        // 1 duplicate.
        var duplicate = digs[0] eq digs[1]
        for (i in 0 until digs.lastIndex) {
            val leftPower = pow(10, digs.lastIndex - i)
            val rightPower = pow(10, digs.lastIndex - i - 1)
            duplicate = duplicate or ((digs[i] / leftPower) eq (digs[i + 1] / rightPower))
        }
        duplicate.post()

        // Confirm our digit sums are in the correct range of PW_MIN..PW_MAX
        // All of the numbers are already multiplied by a power of 10,
        // which means a simple sum will give the correct output.
        sum { digs ge PW_MIN }.post()
        sum { digs le PW_MAX }.post()

        solver.showSolutions() {
            digs.map { it.value }.sum().toString()
        }
        solver.solveAll()
        println("Total solutions: ${solver.solutionCount}")
    }
}
