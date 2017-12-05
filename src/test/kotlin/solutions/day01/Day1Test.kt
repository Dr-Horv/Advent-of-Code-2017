package solutions.day01

import solutions.AbstractDayTest

class Day1Test : AbstractDayTest(Day1()) {
    override fun getPart1Data(): Map<List<String>, String> = mapOf(
            Pair(listOf("1122"), "3"),
            Pair(listOf("1111"), "4"),
            Pair(listOf("1234"), "0"),
            Pair(listOf("91212129"), "9")
    )

    override fun getPart2Data(): Map<List<String>, String> = mapOf(
            Pair(listOf("1212"), "6"),
            Pair(listOf("1221"), "0"),
            Pair(listOf("123425"), "4"),
            Pair(listOf("123123"), "12"),
            Pair(listOf("12131415"), "4")
    )
}