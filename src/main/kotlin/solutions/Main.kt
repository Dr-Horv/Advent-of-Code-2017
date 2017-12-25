package solutions

import solutions.day01.Day1
import solutions.day02.Day2
import solutions.day03.Day3
import solutions.day04.Day4
import solutions.day05.Day5
import solutions.day06.Day6
import solutions.day07.Day7
import solutions.day08.Day8
import solutions.day09.Day9
import solutions.day10.Day10
import solutions.day11.Day11
import solutions.day12.Day12
import solutions.day13.Day13
import solutions.day14.Day14
import solutions.day15.Day15
import solutions.day16.Day16
import solutions.day18.Day18
import solutions.day20.Day20
import solutions.day21.Day21
import solutions.day22.Day22
import solutions.day23.Day23
import solutions.day24.Day24
import solutions.day25.Day25

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
    Day08,
    Day09,
    Day10,
    Day11,
    Day12,
    Day13,
    Day14,
    Day15,
    Day16,
    Day18,
    Day20,
    Day22,
    Day21,
    Day23,
    Day24,
    Day25
}

fun Long.toSeconds(): Double = this / (10e8)
fun Long.toMilliseconds(): Double = this / (10e5)

fun main(args: Array<String>) {

    val time = measureNanoTime {
        val partTwo = false
        val day = Days.Day16
        val input = getInput(day)
        val solver = when (day) {
            Days.Day01 -> Day1()
            Days.Day02 -> Day2()
            Days.Day03 -> Day3()
            Days.Day04 -> Day4()
            Days.Day05 -> Day5()
            Days.Day06 -> Day6()
            Days.Day07 -> Day7()
            Days.Day08 -> Day8()
            Days.Day09 -> Day9()
            Days.Day10 -> Day10()
            Days.Day11 -> Day11()
            Days.Day12 -> Day12()
            Days.Day13 -> Day13()
            Days.Day14 -> Day14()
            Days.Day15 -> Day15()
            Days.Day16 -> Day16()
            Days.Day18 -> Day18()
            Days.Day20 -> Day20()
            Days.Day21 -> Day21()
            Days.Day22 -> Day22()
            Days.Day23 -> Day23()
            Days.Day24 -> Day24()
            Days.Day25 -> Day25()
        }

        printAnswer(day.name, solver.solve(input, partTwo))
    }

    println("Took ${time.toSeconds()} seconds")
    println("Took ${time.toMilliseconds()} milliseconds")


}

fun getInput(day: Days): List<String> {
    val solutions = "src/main/kotlin/solutions"
    return readFile("$solutions/${day.name.toLowerCase()}/input")
}


fun printAnswer(msg: String, answer: String) {
    println("$msg: $answer")
}

