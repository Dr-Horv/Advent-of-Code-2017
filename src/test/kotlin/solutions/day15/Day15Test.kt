package solutions.day15

import solutions.AbstractDayTest


class Day15Test: AbstractDayTest(Day15()) {
    override fun getPart1Data(): List<TestData> = listOf(
            TestData(listOf("Generator A starts with 65",
                    "Generator B starts with 8921"), "588"),
            TestData(listOf("Generator A starts with 512",
                    "Generator B starts with 191"), "567")
    )

    override fun getPart2Data(): List<TestData> = listOf(
            TestData(listOf("Generator A starts with 65",
                    "Generator B starts with 8921"), "309"),
            TestData(listOf("Generator A starts with 512",
                    "Generator B starts with 191"), "323")
    )

}
