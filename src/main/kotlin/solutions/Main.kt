package solutions

import solutions.day01.Day1
import solutions.day02.Day2
import solutions.day03.Day3
import solutions.day04.Day4
import solutions.day05.Day5
import solutions.day06.Day6
import solutions.day07.Day7
import utils.readFile
import kotlin.system.measureNanoTime

enum class Days {
    Day01,
    Day02,
    Day03,
    Day04,
    Day05,
    Day06,
    Day07,
}

fun Long.toSeconds(): Double = this / (10e9)

fun main(args: Array<String>) {

    val time = measureNanoTime {
        val partTwo = true
        val day = Days.Day07
        val input = getInput(day)
        val solver = when (day) {
            Days.Day01 -> Day1()
            Days.Day02 -> Day2()
            Days.Day03 -> Day3()
            Days.Day04 -> Day4()
            Days.Day05 -> Day5()
            Days.Day06 -> Day6()
            Days.Day07 -> Day7()
        }

        printAnswer(day.name, solver.solve(input, partTwo))
    }

    println("Took ${time.toSeconds()} seconds")


}

fun getInput(day: Days): List<String> {
    val solutions = "src/main/kotlin/solutions"
    return readFile("$solutions/${day.name.toLowerCase()}/input")
}


fun printAnswer(msg: String, answer: String) {
    println("$msg: $answer")
}

