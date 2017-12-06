package solutions.day02

import solutions.AbstractDayTest

class Day2Test : AbstractDayTest(Day2()) {
    override fun getPart1Data(): List<TestData> = listOf(
            TestData(listOf(
                    "5 1 9 5",
                    "7 5 3",
                    "2 4 6 8"), "18")
    )

    override fun getPart2Data(): List<TestData> = listOf(
            TestData(listOf(
                    "5 9 2 8",
                    "9 4 7 3",
                    "3 8 6 5"), "9")
    )

}