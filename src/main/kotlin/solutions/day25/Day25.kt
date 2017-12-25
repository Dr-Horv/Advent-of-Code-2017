
package solutions.day25

import solutions.Solver
import utils.splitAtWhitespace
import java.lang.RuntimeException
import java.util.regex.Pattern

enum class Direction {
    LEFT,
    RIGHT
}

fun Int.move(direction: Direction) = when(direction) {
    Direction.LEFT -> this - 1
    Direction.RIGHT -> this + 1
}

fun String.toDirection(): Direction = when(this) {
    "left" -> Direction.LEFT
    "right" -> Direction.RIGHT
    else -> throw RuntimeException("Unparsable direction $this")
}

data class State(val onZero: Triple<Int, Direction, String>, val onOne: Triple<Int, Direction, String>)

class Day25: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {

        val inputAsOneString = input.joinToString("\n")
        val regex = "in state (\\w):\\s+if the current value is (\\d):\\s+- write the value (\\d)\\.\\s+- Move one slot to the (\\w+)\\.\\s+- Continue with state (\\w)\\.\\s+if the current value is (\\d):\\s+- write the value (\\d)\\.\\s+- Move one slot to the (\\w+)\\.\\s+- Continue with state (\\w)\\."

        val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(inputAsOneString)

        val states = mutableMapOf<String, State>()

        while (matcher.find()) {
            val name = matcher.group(1)
            states.put(name, State(
                    Triple(matcher.group(3).toInt(), matcher.group(4).toDirection(), matcher.group(5)),
                    Triple(matcher.group(7).toInt(), matcher.group(8).toDirection(), matcher.group(9))
            ))
        }

        val firstLine = input[0]
        val secondLine = input[1]
        var next = firstLine[firstLine.lastIndex-1].toString()
        var position = 0
        var iterationsLeft = secondLine.splitAtWhitespace()[5].toInt()

        val values = mutableMapOf<Int, Int>().withDefault { 0 }

        while (iterationsLeft > 0) {
            val s = states.getValue(next)
            val value = values.getValue(position)
            val (write, direction, nextState) = if(value == 0) {
                s.onZero
            } else {
                s.onOne
            }

            values[position] = write
            position = position.move(direction)
            next = nextState

            iterationsLeft--
        }

        return values.values.sum().toString()




    }
}
