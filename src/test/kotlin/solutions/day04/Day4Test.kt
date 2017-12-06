package solutions.day04

import solutions.AbstractDayTest

class Day4Test: AbstractDayTest(Day4()) {
    override fun getPart1Data(): List<TestData> = listOf(
            TestData(listOf("aa bb cc dd ee"), "1"),
            TestData(listOf("aa bb cc dd aa"), "0"),
            TestData(listOf("aa bb cc dd aaa"), "1")
    )

    override fun getPart2Data(): List<TestData> = listOf(
            TestData(listOf("abcde fghij"), "1"),
            TestData(listOf("abcde xyz ecdab"), "0"),
            TestData(listOf("a ab abc abd abf abj"), "1"),
            TestData(listOf("iiii oiii ooii oooi oooo"), "1"),
            TestData(listOf("oiii ioii iioi iiio"), "0")
    )

}