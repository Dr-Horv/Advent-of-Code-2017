
package solutions.day06

import solutions.AbstractDayTest

class Day6Test: AbstractDayTest(Day6()) {
    override fun getPart1Data(): Map<List<String>, String> = mapOf(
            Pair(listOf("0 2 7 0"), "5")
    )

    override fun getPart2Data(): Map<List<String>, String> = mapOf(
            Pair(listOf("0 2 7 0"), "4")
    )

}
