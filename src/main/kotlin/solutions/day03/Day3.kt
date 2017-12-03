package solutions.day03

import solutions.Solver

data class Cursor(var x: Int, var y:Int)
data class Coordinate(val x: Int, val y: Int)
enum class Direction {
    UP,
    LEFT,
    RIGHT,
    DOWN
}

fun Cursor.manhattanDistance() : Int = Math.abs(x) + Math.abs(y)

data class CircleInfo(val circle: Int, val numbers: Int)


class Day3: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {

        val target = input.first().toInt()
        if(partTwo) {
            return doPartTwo(target)
        }
        val circleInfo = determineCircle(target)

        val number = circleInfo.numbers + (circleInfo.circle-2)
        val cursor = Cursor(circleInfo.circle - 1, 0)

        val valueAtTopRightCorner = number + (circleInfo.circle - 1)
        if(target <= valueAtTopRightCorner) {
            val steps = (target-number) - 1
            cursor.y = cursor.y + steps
            return cursor.manhattanDistance().toString()
        }

        cursor.y = circleInfo.circle - 1

        val stepsToTraverseSide = (circleInfo.circle - 1) * 2

        val valueAtTopLeftCorner = valueAtTopRightCorner + stepsToTraverseSide
        if(target <= valueAtTopLeftCorner) {
            val steps = target - valueAtTopRightCorner - 1
            cursor.x = cursor.x - steps
            return cursor.manhattanDistance().toString()
        }

        cursor.x = cursor.x - stepsToTraverseSide

        val valueAtBottomLeftCorner = valueAtTopLeftCorner + stepsToTraverseSide
        if(target <= valueAtBottomLeftCorner) {
            val steps = target - valueAtTopLeftCorner - 1
            cursor.y = cursor.y - steps
            return cursor.manhattanDistance().toString()
        }

        cursor.y = cursor.y - stepsToTraverseSide

        val valueAtBottomRightCorner = valueAtBottomLeftCorner + stepsToTraverseSide
        if(target <= valueAtBottomRightCorner) {
            val steps = target - valueAtBottomLeftCorner - 1
            cursor.x = cursor.x + steps
            return  cursor.manhattanDistance().toString()
        }

        cursor.x = cursor.x + stepsToTraverseSide

        val valueBeforeBackAtStart = valueAtBottomRightCorner + (stepsToTraverseSide / 2 - 1)

        if(target <= valueBeforeBackAtStart) {
            val steps = target - valueAtBottomRightCorner - 1
            cursor.y = cursor.y + steps
            return cursor.manhattanDistance().toString()
        }


        throw RuntimeException("Not found")
    }

    private fun doPartTwo(target: Int): String {
        val memory: MutableMap<Coordinate, Int> = mutableMapOf<Coordinate, Int>().withDefault { 0 }
        val start = Coordinate(0, 0)
        memory.put(start, 1)
        return traverse(target, start, memory, Direction.RIGHT, 1)
    }

    private fun Coordinate.step(direction: Direction) : Coordinate = when(direction) {
        Direction.UP -> Coordinate(x, y+1)
        Direction.LEFT -> Coordinate(x-1, y)
        Direction.RIGHT -> Coordinate(x+1, y)
        Direction.DOWN -> Coordinate(x, y-1)
    }

    private fun traverse(target: Int, curr: Coordinate, memory: MutableMap<Coordinate, Int>, direction: Direction, steps: Int): String {
        if(steps == 0) {
            val circleInfo = determineCircle(memory.size)
            val stepsNext = (circleInfo.circle - 1) * 2
            return when(direction) {
                Direction.UP -> traverse(target, curr, memory, Direction.LEFT, stepsNext)
                Direction.LEFT ->  traverse(target, curr, memory, Direction.DOWN, stepsNext)
                Direction.DOWN -> traverse(target, curr, memory, Direction.RIGHT, stepsNext+1)
                Direction.RIGHT ->  traverse(target, curr, memory, Direction.UP, stepsNext-1)
            }
        }

        val next = curr.step(direction)
        val sum = getSum(next, memory)

        if(sum > target) {
            return sum.toString()
        }
        memory.put(next, getSum(next, memory))
        return traverse(target, next, memory, direction, steps-1)
    }

    private fun getSum(coordinate: Coordinate, memory: MutableMap<Coordinate, Int>): Int {

        return memory.getValue(coordinate.step(Direction.UP)) +
                memory.getValue(coordinate.step(Direction.LEFT)) +
                memory.getValue(coordinate.step(Direction.RIGHT)) +
                memory.getValue(coordinate.step(Direction.DOWN)) +
                memory.getValue(Coordinate(coordinate.x+1, coordinate.y+1)) +
                memory.getValue(Coordinate(coordinate.x-1, coordinate.y+1)) +
                memory.getValue(Coordinate(coordinate.x+1, coordinate.y-1)) +
                memory.getValue(Coordinate(coordinate.x-1, coordinate.y-1))
    }

    private fun determineCircle(target: Int): CircleInfo {
        var steps = 0
        if(target > 1) {
            steps += 1
        }

        var circle = 2
        while(true) {
            val nextSteps = steps + (circle-1) * 8
            if(nextSteps > target) {
                return CircleInfo(circle, steps)
            }
            circle++
            steps = nextSteps
        }
    }

}