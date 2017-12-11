
package solutions.day11

import solutions.AbstractDayTest

class Day11Test: AbstractDayTest(Day11()) {
    override fun getPart1Data(): List<TestData> = listOf(
           TestData(listOf("ne,ne,ne"), "3"),
           TestData(listOf("ne,ne,sw,sw"), "0"),
           TestData(listOf("ne,ne,s,s"), "2"),
           TestData(listOf("se,sw,se,sw,sw"), "3")
    )

    override fun getPart2Data(): List<TestData> = listOf(
           TestData(listOf(""), "")
    )

}
