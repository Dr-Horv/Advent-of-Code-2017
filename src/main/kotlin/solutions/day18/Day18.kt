
package solutions.day18

import solutions.Solver
import utils.splitAtWhitespace
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.TimeUnit

class Program(number: Long,
              private val instructions: List<String>,
              private val sendQueue: BlockingQueue<Long>,
              private val receiveQueue: BlockingQueue<Long>): Runnable {

    private val registers = mutableMapOf(Pair("p", number)).withDefault { 0L }
    private var sent = 0

    override fun run() {
        var index = 0L

        loop@ while (index < instructions.size) {
            val instruction = instructions[index.toInt()].splitAtWhitespace()
            when (instruction[0]) {
                "set" -> set(registers, instruction[1], instruction[2])
                "add" -> add(registers, instruction[1], instruction[2])
                "mul" -> mul(registers, instruction[1], instruction[2])
                "mod" -> mod(registers, instruction[1], instruction[2])
                "snd" -> {
                    sendQueue.add(getValue(registers, instruction[1]))
                    sent++
                }
                "rcv" -> {
                    val received = receiveQueue.poll(10, TimeUnit.SECONDS) ?: return
                    registers.put(instruction[1], received)
                }
                "jgz" -> {
                    if (getValue(registers, instruction[1]) > 0L) {
                        index += getValue(registers, instruction[2])
                        continue@loop
                    }
                }
            }
            index++
        }

    }

    fun timesSent(): Int {
        return sent
    }

}

class Day18: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {
        if(!partTwo) {
            return part1(input)
        }

        val q1 = LinkedBlockingDeque<Long>()
        val q2 = LinkedBlockingDeque<Long>()
        val p0 = Program(0, input, q1, q2)
        val p1 = Program(1, input, q2, q1)

        val t1 = Thread(p0)
        val t2 = Thread(p1)
        t1.start()
        t2.start()

        while (t1.isAlive && t2.isAlive) {
            Thread.sleep(1000L)
        }

        return p1.timesSent().toString()

    }

    private fun part1(input: List<String>): String {
        val registers = mutableMapOf<String, Long>().withDefault { 0L }
        val instructions = input

        var index = 0L
        var sound = -1L
        var recovered = -1L

        loop@ while (index < instructions.size) {
            val instruction = instructions[index.toInt()].splitAtWhitespace()
            when (instruction[0]) {
                "set" -> set(registers, instruction[1], instruction[2])
                "add" -> add(registers, instruction[1], instruction[2])
                "mul" -> mul(registers, instruction[1], instruction[2])
                "mod" -> mod(registers, instruction[1], instruction[2])
                "snd" -> {
                    sound = getValue(registers, instruction[1])
                }
                "rcv" -> {
                    if (getValue(registers, instruction[1]) != 0L) {
                        recovered = sound
                        return recovered.toString()
                    }
                }
                "jgz" -> {
                    if (getValue(registers, instruction[1]) > 0L) {
                        index += getValue(registers, instruction[2])
                        continue@loop
                    }
                }
            }
            index++
        }

        return ""
    }
}

private fun mod(registers: MutableMap<String, Long>, s: String, toInt: String) {
    registers.put(s, registers.getValue(s) % getValue(registers, toInt))
}

private fun mul(registers: MutableMap<String, Long>, s: String, toInt: String) {
    registers.put(s, registers.getValue(s) * getValue(registers, toInt))
}

private fun add(registers: MutableMap<String, Long>, s: String, toInt: String) {
    registers.put(s, registers.getValue(s) + getValue(registers, toInt))
}

private fun set(registers: MutableMap<String, Long>, s: String, toInt: String) {
    registers.put(s, getValue(registers, toInt))
}

private fun getValue(registers: MutableMap<String, Long>, s: String): Long {
    return try {
        s.toLong()
    } catch (e: NumberFormatException) {
        registers.getValue(s)
    }
}
