package solutions.day01

import solutions.AbstractDayTest

class Day1Test : AbstractDayTest(Day1()) {
    override fun getPart1Data(): List<TestData> = listOf(
            TestData(listOf("1122"), "3"),
            TestData(listOf("1111"), "4"),
            TestData(listOf("1234"), "0"),
            TestData(listOf("91212129"), "9")
    )

    override fun getPart2Data(): List<TestData> = listOf(
            TestData(listOf("1212"), "6"),
            TestData(listOf("1221"), "0"),
            TestData(listOf("123425"), "4"),
            TestData(listOf("123123"), "12"),
            TestData(listOf("12131415"), "4")
    )
}