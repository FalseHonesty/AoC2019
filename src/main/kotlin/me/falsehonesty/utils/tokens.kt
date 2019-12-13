package me.falsehonesty.utils

class TokenStream(val input: String) {
    var index = 0

    fun lastlast(): Char {
        return input[index - 2]
    }

    fun last(): Char {
        return input[index - 1]
    }

    fun next(): Char {
        return input[index++]
    }

    fun nextnext(): Char {
        return input[index + 2]
    }

    fun hasNext(): Boolean {
        return index < input.length - 1
    }
}