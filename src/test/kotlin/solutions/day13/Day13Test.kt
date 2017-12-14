
package solutions.day13

import solutions.AbstractDayTest

class Day13Test: AbstractDayTest(Day13()) {
    override fun getPart1Data(): List<TestData> = listOf(
           TestData(listOf("0: 3",
                   "1: 2",
                   "4: 4",
                   "6: 4"), "24")
    )

    override fun getPart2Data(): List<TestData> = listOf(
            TestData(listOf("0: 3",
                    "1: 2",
                    "4: 4",
                    "6: 4"), "10")

    )

}
