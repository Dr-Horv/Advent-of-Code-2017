package solutions.day10

import solutions.AbstractDayTest

class Day10Test: AbstractDayTest(Day10()) {
    override fun getPart1Data(): List<TestData> = listOf<TestData>(
                TestData(listOf("3, 4, 1, 5"), "12"),
                TestData(listOf("34,88,2,222,254,93,150,0,199,255,39,32,137,136,1,167"), "54675")
        )

    override fun getPart2Data(): List<TestData> = listOf<TestData>(
            TestData(listOf(""), "a2582a3a0e66e6e86e3812dcb672a272"),
            TestData(listOf("AoC 2017"), "33efeb34ea91902bb2f59c9920caa6cd"),
            TestData(listOf("1,2,3"), "3efbe78a8d82f29979031a4aa0b16a9d"),
            TestData(listOf("1,2,4"), "63960835bcdc130f0b66d7ff4f6a5a8e")
    )
}