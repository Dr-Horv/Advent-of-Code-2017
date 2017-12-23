
package solutions.day22

import solutions.AbstractDayTest

class Day22Test: AbstractDayTest(Day22()) {
    override fun getPart1Data(): List<TestData> = listOf(
           TestData(listOf("..#",
                   "#..",
                   "..."), "5587")
    )

    override fun getPart2Data(): List<TestData> = listOf(
           TestData(listOf("..#",
                   "#..",
                   "..."), "2511944")
    )

}
