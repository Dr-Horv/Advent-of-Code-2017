package solutions.day04

import solutions.AbstractDayTest

class Day4Test: AbstractDayTest(Day4()) {
    override fun getPart1Data(): Map<List<String>, String> = mapOf(
            Pair(listOf("aa bb cc dd ee"), "1"),
            Pair(listOf("aa bb cc dd aa"), "0"),
            Pair(listOf("aa bb cc dd aaa"), "1")
    )

    override fun getPart2Data(): Map<List<String>, String> = mapOf(
            Pair(listOf("abcde fghij"), "1"),
            Pair(listOf("abcde xyz ecdab"), "0"),
            Pair(listOf("a ab abc abd abf abj"), "1"),
            Pair(listOf("iiii oiii ooii oooi oooo"), "1"),
            Pair(listOf("oiii ioii iioi iiio"), "0")
    )

}