
package solutions.day23

import solutions.AbstractDayTest

class Day23Test: AbstractDayTest(Day23()) {
    override fun getPart1Data(): List<TestData> = listOf(
            TestData(listOf("set b 65",
                    "set c b",
                    "jnz a 2",
                    "jnz 1 5",
                    "mul b 100",
                    "sub b -100000",
                    "set c b",
                    "sub c -17000",
                    "set f 1",
                    "set d 2",
                    "set e 2",
                    "set g d",
                    "mul g e",
                    "sub g b",
                    "jnz g 2",
                    "set f 0",
                    "sub e -1",
                    "set g e",
                    "sub g b",
                    "jnz g -8",
                    "sub d -1",
                    "set g d",
                    "sub g b",
                    "jnz g -13",
                    "jnz f 2",
                    "sub h -1",
                    "set g b",
                    "sub g c",
                    "jnz g 2",
                    "jnz 1 3",
                    "sub b -17",
                    "jnz 1 -23"), "3969")
    )

    override fun getPart2Data(): List<TestData> = listOf(
           TestData(listOf("set b 65",
                   "set c b",
                   "jnz a 2",
                   "jnz 1 5",
                   "mul b 100",
                   "sub b -100000",
                   "set c b",
                   "sub c -17000",
                   "set f 1",
                   "set d 2",
                   "set e 2",
                   "set g d",
                   "mul g e",
                   "sub g b",
                   "jnz g 2",
                   "set f 0",
                   "sub e -1",
                   "set g e",
                   "sub g b",
                   "jnz g -8",
                   "sub d -1",
                   "set g d",
                   "sub g b",
                   "jnz g -13",
                   "jnz f 2",
                   "sub h -1",
                   "set g b",
                   "sub g c",
                   "jnz g 2",
                   "jnz 1 3",
                   "sub b -17",
                   "jnz 1 -23"), "917")
    )

}
