package solutions.day03

import solutions.AbstractDayTest

class Day3Test: AbstractDayTest(Day3()) {
    override fun getPart1Data(): List<TestData> = listOf(
            TestData(listOf("1"), "0"),
            TestData(listOf("12"), "3"),
            TestData(listOf("23"), "2"),
            TestData(listOf("1024"), "31")
    )

    override fun getPart2Data(): List<TestData> = listOf(
            TestData(listOf("2"), "4"),
            TestData(listOf("10"), "11"),
            TestData(listOf("122"), "133"),
            TestData(listOf("340"), "351")
    )

}