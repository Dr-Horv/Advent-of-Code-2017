
package solutions.day11

import solutions.Solver
data class Position(val x: Int, val y: Int)

enum class Direction {
    N,
    NE,
    SE,
    S,
    SW,
    NW
}

fun String.toDirection(): Direction = when(this) {
    "n" -> Direction.N
    "ne" -> Direction.NE
    "se" -> Direction.SE
    "s" -> Direction.S
    "sw" -> Direction.SW
    "nw" -> Direction.NW
    else -> throw RuntimeException("Unparsable direction")
}

fun Position.go(dir: Direction) : Position = when(dir){
    Direction.N -> Position(x, y + 2)
    Direction.NE -> Position(x+1, y+1)
    Direction.SE -> Position(x+1, y-1)
    Direction.S -> Position(x, y-2)
    Direction.SW -> Position(x-1, y-1)
    Direction.NW -> Position(x-1, y+1)
}

fun Position.distance(): Int {
    val xPos = Math.abs(x)
    val yPos = Math.abs(y)
    return when {
        yPos > xPos -> xPos + yPos/2
        else -> xPos
    }
}


class Day11: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {


        val map = input.first()
                .split(",")
                .map(String::trim)
                .map(String::toDirection)

        var curr = Position(0,0)
        var furthest = curr

        map.forEach {
            curr = curr.go(it)
            if(partTwo && curr.distance() > furthest.distance()) {
                furthest = curr
            }
        }

        return if(!partTwo) {
            (curr.distance()).toString()
        } else {
            (furthest.distance()).toString()
        }

    }


}
