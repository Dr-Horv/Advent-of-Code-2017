package solutions.day22

import solutions.Solver
import utils.Coordinate
import utils.Direction
import utils.step

fun Direction.left() = when (this) {
    Direction.UP -> Direction.LEFT
    Direction.LEFT -> Direction.DOWN
    Direction.RIGHT -> Direction.UP
    Direction.DOWN -> Direction.RIGHT
}

fun Direction.right() = when (this) {
    Direction.UP -> Direction.RIGHT
    Direction.LEFT -> Direction.UP
    Direction.RIGHT -> Direction.DOWN
    Direction.DOWN -> Direction.LEFT
}

private fun Direction.reverse(): Direction = when (this) {
    Direction.UP -> Direction.DOWN
    Direction.LEFT -> Direction.RIGHT
    Direction.RIGHT -> Direction.LEFT
    Direction.DOWN -> Direction.UP
}

enum class NodeState {
    CLEAN,
    WEAKENED,
    INFECTED,
    FLAGGED

}

class Day22 : Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {
        val grid = mutableMapOf<Coordinate, NodeState>().withDefault { NodeState.CLEAN }

        val startY = (input.size / 2)

        input.forEachIndexed { y, line ->
            val startX = -(line.length / 2)
            line.forEachIndexed { i, c ->
                val coordinate = Coordinate(startX + i, startY - y)
                when (c) {
                    '#' -> grid.put(coordinate, NodeState.INFECTED)
                }
            }
        }

        var carrier = Coordinate(0, 0)
        var direction = Direction.UP

        val nodeModifier: (s: NodeState) -> NodeState = if (partTwo) {
            { s ->
                when (s) {
                    NodeState.CLEAN -> NodeState.WEAKENED
                    NodeState.WEAKENED -> NodeState.INFECTED
                    NodeState.INFECTED -> NodeState.FLAGGED
                    NodeState.FLAGGED -> NodeState.CLEAN
                }
            }
        } else {
            { s ->
                when (s) {
                    NodeState.CLEAN -> NodeState.INFECTED
                    NodeState.INFECTED -> NodeState.CLEAN
                    else -> throw RuntimeException("Invalid state $s")
                }
            }
        }

        val bursts = if(partTwo) {
            10_000_000
        } else {
            10_000
        }

        var infectionBursts = 0
        for (burst in 1..bursts) {
            val state = grid.getValue(carrier)
            direction = when (state) {
                NodeState.CLEAN -> direction.left()
                NodeState.WEAKENED -> direction
                NodeState.INFECTED -> direction.right()
                NodeState.FLAGGED -> direction.reverse()
            }

            val nextState = nodeModifier(state)
            if(nextState == NodeState.INFECTED) {
                infectionBursts++
            }

            grid.put(carrier, nextState)

            carrier = carrier.step(direction)
        }

        return infectionBursts.toString()

    }

}
