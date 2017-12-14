
package solutions.day14

import solutions.AbstractDayTest

class Day14Test: AbstractDayTest(Day14()) {
    override fun getPart1Data(): List<TestData> = listOf(
           TestData(listOf("flqrgnkx"), "8108")
    )

    override fun getPart2Data(): List<TestData> = listOf(
           TestData(listOf("flqrgnkx"), "1242")
    )

}
