
package solutions.day08

import solutions.AbstractDayTest

class Day8Test: AbstractDayTest(Day8()) {
    override fun getPart1Data(): List<TestData> = listOf(
           TestData(listOf("b inc 5 if a > 1",
                   "a inc 1 if b < 5",
                   "c dec -10 if a >= 1",
                   "c inc -20 if c == 10"), "1")
    )

    override fun getPart2Data(): List<TestData> = listOf(
            TestData(listOf("b inc 5 if a > 1",
                    "a inc 1 if b < 5",
                    "c dec -10 if a >= 1",
                    "c inc -20 if c == 10"), "10")
    )

}
