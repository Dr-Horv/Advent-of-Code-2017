package solutions.day23

import solutions.Solver
import utils.*

class Day23 : Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {
        if (partTwo) {
            return doPartTwo()
        }

        val registers = mutableMapOf<String, Long>().withDefault { 0 }
        val instructions = input

        var index = 0L
        var iteration = 0
        var mulUsed = 0
        loop@ while (index < instructions.size) {
            iteration++
            val instruction = instructions[index.toInt()].splitAtWhitespace()
            when (instruction[0]) {
                "set" -> set(registers, instruction[1], instruction[2])
                "sub" -> sub(registers, instruction[1], instruction[2])
                "mul" -> {
                    mul(registers, instruction[1], instruction[2])
                    mulUsed++
                }
                "jnz" -> {
                    if (getValue(registers, instruction[1]) != 0L) {
                        index += getValue(registers, instruction[2])
                        continue@loop
                    }
                }
            }
            index++
        }

        return mulUsed.toString()
    }

    private fun  doPartTwo(): String {
        var a = 1
        var b = 0
        var c = 0
        var d = 0
        var e = 0
        var f = 0
        var g = 0
        var h = 0


        b = 65 // set b 65
        c = b // set c b
        if(a != 0) {
            b = b * 100 // mul b 100
            b = b + 100_000 // sub b -100_000
            c = b // set c b
            c = c + 17_000 // sub c -17_000
        }

        loop3@ while (true) {
            f = 1 // set f 1
            d = 2 // set d 2
            loop2@ while (true) {
                loop1@ while (true) {
                    if(b % d == 0) {
                        f = 0
                    }
                    e = b
                    g = e - b // sub g b
                    if (g == 0) { // jnz g -8
                        break@loop1
                    }
                }

                d++ // sub d -1
                g = d - b // sub g b
                if (g == 0) { // jnz g -13
                    break@loop2
                }
            }
            if (f == 0) { // jnz f 2
                h++ // sub h -1
            }
            g = b - c // sub g c
            if (g == 0) { // jnz g 2, jnz 1 3
                break@loop3
            }
            b = b + 17 // sub - 17
            // jnz 1 -23
        }

        return h.toString()

    }
}
