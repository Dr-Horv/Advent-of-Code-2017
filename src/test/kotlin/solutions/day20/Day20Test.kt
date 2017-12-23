
package solutions.day20

import solutions.AbstractDayTest

class Day20Test: AbstractDayTest(Day20()) {
    override fun getPart1Data(): List<TestData> = listOf(
           TestData(listOf("p=< 3,0,0>, v=< 2,0,0>, a=<-1,0,0>",
                   "p=< 4,0,0>, v=< 0,0,0>, a=<-2,0,0>"), "0")
    )

    override fun getPart2Data(): List<TestData> = listOf(
           TestData(listOf("p=<-6,0,0>, v=< 3,0,0>, a=< 0,0,0>",
                   "p=<-4,0,0>, v=< 2,0,0>, a=< 0,0,0>",
                   "p=<-2,0,0>, v=< 1,0,0>, a=< 0,0,0>",
                   "p=< 3,0,0>, v=<-1,0,0>, a=< 0,0,0>"), "1")
    )

}
