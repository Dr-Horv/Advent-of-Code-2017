package solutions.day05

import solutions.AbstractDayTest

class Day5Test: AbstractDayTest(Day5()) {
    override fun getPart1Data(): Map<List<String>, String> = mapOf(
            Pair(listOf("0",
                    "3",
                    "0",
                    "1",
                    "-3"), "5")
    )

    override fun getPart2Data(): Map<List<String>, String> = mapOf(
            Pair(listOf("0",
                    "3",
                    "0",
                    "1",
                    "-3"), "10")
    )
}
