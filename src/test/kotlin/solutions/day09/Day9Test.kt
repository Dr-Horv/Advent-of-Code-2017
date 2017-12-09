package solutions.day09

import solutions.AbstractDayTest


class Day9Test: AbstractDayTest(Day9()) {
    override fun getPart1Data(): List<TestData> = listOf(
            TestData(listOf("{}"), "1"),
            TestData(listOf("{{{}}}"), "6"),
            TestData(listOf("{{},{}}"), "5"),
            TestData(listOf("{{{},{},{{}}}}"), "16"),
            TestData(listOf("{<a>,<a>,<a>,<a>}"), "1"),
            TestData(listOf("{{<ab>},{<ab>},{<ab>},{<ab>}}"), "9"),
            TestData(listOf("{{<!!>},{<!!>},{<!!>},{<!!>}}"), "9"),
            TestData(listOf("{{<a!>},{<a!>},{<a!>},{<ab>}}"), "3")
    )

    override fun getPart2Data(): List<TestData> = listOf(
            TestData(listOf("{<>}"), "0"),
            TestData(listOf("{<random characters>}"), "17"),
            TestData(listOf("{<<<<>}"), "3"),
            TestData(listOf("{<{!>}>}"), "2"),
            TestData(listOf("{<!!>}"), "0"),
            TestData(listOf("{<!!!>>}"), "0"),
            TestData(listOf("{<{o\"i!a,<{i<a>}"), "10")
    )

}
