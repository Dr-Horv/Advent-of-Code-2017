
package solutions.day12

import solutions.AbstractDayTest

class Day12Test: AbstractDayTest(Day12()) {
    override fun getPart1Data(): List<TestData> = listOf(
           TestData(listOf("0 <-> 2",
                   "1 <-> 1",
                   "2 <-> 0, 3, 4",
                   "3 <-> 2, 4",
                   "4 <-> 2, 3, 6",
                   "5 <-> 6",
                   "6 <-> 4, 5"), "6")
    )

    override fun getPart2Data(): List<TestData> = listOf(
            TestData(listOf("0 <-> 2",
                    "1 <-> 1",
                    "2 <-> 0, 3, 4",
                    "3 <-> 2, 4",
                    "4 <-> 2, 3, 6",
                    "5 <-> 6",
                    "6 <-> 4, 5"), "2")
    )

}
