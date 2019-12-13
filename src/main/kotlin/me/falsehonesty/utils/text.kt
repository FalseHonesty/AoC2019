package me.falsehonesty.utils

fun String.splitWS() = this.split("\\s+".toRegex())

fun String.with(index: Int, value: Char): String {
    val newStr = if (index < length) this else {
        this + " ".repeat(index - length + 1)
    }

    return newStr.substring(0, index) + value + newStr.substring(index + 1, newStr.length)
}

operator fun String.times(repeat: Int): String {
    return this.repeat(repeat)
}
