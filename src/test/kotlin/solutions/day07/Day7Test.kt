
package solutions.day07

import solutions.AbstractDayTest

class Day7Test: AbstractDayTest(Day7()) {
    override fun getPart1Data(): List<TestData> = listOf(
           TestData(listOf("pbga (66)",
                   "xhth (57)",
                   "ebii (61)",
                   "havc (66)",
                   "ktlj (57)",
                   "fwft (72) -> ktlj, cntj, xhth",
                   "qoyq (66)",
                   "padx (45) -> pbga, havc, qoyq",
                   "tknk (41) -> ugml, padx, fwft",
                   "jptl (61)",
                   "ugml (68) -> gyxo, ebii, jptl",
                   "gyxo (61)",
                   "cntj (57)"), "tknk")
    )

    override fun getPart2Data(): List<TestData> = listOf(
            TestData(listOf("pbga (66)",
                    "xhth (57)",
                    "ebii (61)",
                    "havc (66)",
                    "ktlj (57)",
                    "fwft (72) -> ktlj, cntj, xhth",
                    "qoyq (66)",
                    "padx (45) -> pbga, havc, qoyq",
                    "tknk (41) -> ugml, padx, fwft",
                    "jptl (61)",
                    "ugml (68) -> gyxo, ebii, jptl",
                    "gyxo (61)",
                    "cntj (57)"), "60")
    )

}
