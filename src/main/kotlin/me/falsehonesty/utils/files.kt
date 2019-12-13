package me.falsehonesty.utils

fun resource(name: String): List<String>
        = Any::class.java.getResourceAsStream(name).bufferedReader().readLines()