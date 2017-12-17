package solutions.day15

import solutions.Solver
import utils.splitAtWhitespace

class Generator(private var value: Long, private val factor: Int, private val divisableBy: Int) {
    fun next(): Long {
        while(true) {
            value = (value * factor) % 2147483647
            if(value % divisableBy == 0L) {
                return value
            }
        }
    }
}

fun Long.toBinaryString(): String {
    val s = java.lang.Long.toBinaryString(this)
    return if(s.length < 16) {
        s.padStart(16, '0')
    } else {
        s
    }
}

class Day15: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {

        val generators = input
                .map(String::splitAtWhitespace)
                .mapIndexed {index, list ->
                    when(index) {
                        0 -> when(partTwo) {
                            true -> Generator(list.last().toLong(), 16807, 4)
                            false -> Generator(list.last().toLong(), 16807, 1)
                        }
                        1 -> when(partTwo) {
                            true -> Generator(list.last().toLong(), 48271, 8)
                            false -> Generator(list.last().toLong(), 48271, 1)
                        }
                        else -> throw RuntimeException("Invalid index")
                    }
                }

        var count = 0
        val iterations = if(partTwo) {
            5_000_000
        } else {
            40_000_000
        }

        for (i in 1..iterations) {
            val (a, b) = generators
                    .map(Generator::next)


            val aStr = a.toBinaryString()
            val bStr = b.toBinaryString()

            val first = aStr.substring(aStr.length - 16)
            val second = bStr.substring(bStr.length - 16)

            if(first == second) {
                count++
            }



        }

        return count.toString()
    }

}