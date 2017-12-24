
package solutions.day24

import solutions.AbstractDayTest

class Day24Test: AbstractDayTest(Day24()) {
    override fun getPart1Data(): List<TestData> = listOf(
           TestData(listOf("0/2",
                   "2/2",
                   "2/3",
                   "3/4",
                   "3/5",
                   "0/1",
                   "10/1",
                   "9/10"), "31")
    )

    override fun getPart2Data(): List<TestData> = listOf(
            TestData(listOf("0/2",
                    "2/2",
                    "2/3",
                    "3/4",
                    "3/5",
                    "0/1",
                    "10/1",
                    "9/10"), "19")
    )

}
