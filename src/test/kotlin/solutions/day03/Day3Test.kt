package solutions.day03

import solutions.AbstractDayTest

class Day3Test: AbstractDayTest(Day3()) {
    override fun getPart1Data(): Map<List<String>, String> = mapOf(
            Pair(listOf("1"), "0"),
            Pair(listOf("12"), "3"),
            Pair(listOf("23"), "2"),
            Pair(listOf("1024"), "31")
    )

    override fun getPart2Data(): Map<List<String>, String> = mapOf(
            Pair(listOf("2"), "4"),
            Pair(listOf("10"), "11"),
            Pair(listOf("122"), "133"),
            Pair(listOf("340"), "351")
    )

}