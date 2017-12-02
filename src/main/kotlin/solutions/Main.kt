package solutions

import solutions.day01.Day1
import utils.readFile

enum class Days {
    Day01,
}

fun main(args: Array<String>) {

    val partTwo = true
    val day = Days.Day01
    val input = getInput(day)
    val solver = when (day) {
        Days.Day01 -> Day1()
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

