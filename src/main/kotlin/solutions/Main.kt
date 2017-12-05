package solutions

import solutions.day01.Day1
import solutions.day02.Day2
import solutions.day03.Day3
import solutions.day04.Day4
import solutions.day05.Day5
import utils.readFile

enum class Days {
    Day01,
    Day02,
    Day03,
    Day04,
    Day05
}

fun main(args: Array<String>) {

    val partTwo = true
    val day = Days.Day05
    val input = getInput(day)
    val solver = when (day) {
        Days.Day01 -> Day1()
        Days.Day02 -> Day2()
        Days.Day03 -> Day3()
        Days.Day04 -> Day4()
        Days.Day05 -> Day5()
    }

    printAnswer(day.name, solver.solve(input, partTwo))
}

fun getInput(day: Days): List<String> {
    val solutions = "src/main/kotlin/solutions"
    return readFile("$solutions/${day.name.toLowerCase()}/input")
}


fun printAnswer(msg: String, answer: String) {
    println("$msg: $answer")
}

