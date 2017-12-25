
package solutions.day25

import solutions.AbstractDayTest

class Day25Test: AbstractDayTest(Day25()) {
    override fun getPart1Data(): List<TestData> = listOf(
           TestData(listOf("Begin in state A.",
                   "Perform a diagnostic checksum after 6 steps.",
                   "",
                   "In state A:",
                   "  If the current value is 0:",
                   "    - Write the value 1.",
                   "    - Move one slot to the right.",
                   "    - Continue with state B.",
                   "  If the current value is 1:",
                   "    - Write the value 0.",
                   "    - Move one slot to the left.",
                   "    - Continue with state B.",
                   "",
                   "In state B:",
                   "  If the current value is 0:",
                   "    - Write the value 1.",
                   "    - Move one slot to the left.",
                   "    - Continue with state A.",
                   "  If the current value is 1:",
                   "    - Write the value 1.",
                   "    - Move one slot to the right.",
                   "    - Continue with state A."), "3")
    )

    override fun getPart2Data(): List<TestData> = listOf(
           TestData(listOf(""), "")
    )

}
