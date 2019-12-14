package me.falsehonesty.utils

fun day(day: Int) = resource("/day${day}in.txt")

fun resource(name: String): List<String> = Any::class.java.getResourceAsStream(name).bufferedReader().readLines()