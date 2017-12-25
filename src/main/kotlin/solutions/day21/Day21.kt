
package solutions.day21

import solutions.Solver
import utils.Coordinate
import utils.plus

fun printMap(map: Map<Coordinate, Boolean>) {
    val size = determineSize(map)

    for (y in 0 until size) {
        for(x in 0 until size) {
            val value = map.getOrDefault(Coordinate(x, y), false)
            if(value) {
                print("#")
            } else {
                print(".")
            }
        }
        println()
    }
    println()
}

private fun determineSize(map: Map<Coordinate, Boolean>): Int {
    return Math.sqrt(map.keys.size.toDouble()).toInt()
}

fun Char.toBoolean() = when(this) {
    '#' -> true
    '.' -> false
    else -> throw RuntimeException("Unparsable char $this")
}


class Rule(from: String, to: String) {
    private val orig: Map<Coordinate, Boolean>
    private val cases: List<Map<Coordinate, Boolean>>
    val result: Map<Coordinate, Boolean>
    val size: Int
    private val resultSize: Int

    init {
        orig = mutableMapOf()
        cases = mutableListOf()
        result = mutableMapOf()

        val split = from.split("/")
        size = split.size

        val one = mutableMapOf<Coordinate, Boolean>()
        val two = mutableMapOf<Coordinate, Boolean>()
        val three = mutableMapOf<Coordinate, Boolean>()
        val four = mutableMapOf<Coordinate, Boolean>()
        val five = mutableMapOf<Coordinate, Boolean>()
        val six= mutableMapOf<Coordinate, Boolean>()
        val seven = mutableMapOf<Coordinate, Boolean>()
        split.map { it.map(Char::toBoolean) }
            .forEachIndexed { y, bl ->
                bl.forEachIndexed { x, b ->
                    orig.put(Coordinate(x,y), b)
                    one.put((Coordinate(size-1-x, y)), b)

                    two.put(Coordinate(size-1-y, x), b)
                    three.put(Coordinate(size-1-y, size-1-x), b)

                    four.put(Coordinate(size-1-x, size-1-y), b)
                    five.put(Coordinate(x, size-1-y), b)

                    six.put(Coordinate(y, size-1-x), b)
                    seven.put(Coordinate(y, x), b)
                }
            }


        cases.add(orig)
        cases.add(one)
        cases.add(two)
        cases.add(three)
        cases.add(four)
        cases.add(five)
        cases.add(six)
        cases.add(seven)

        val toSplit = to.split("/")

        resultSize = toSplit.size
        toSplit.map { it.map(Char::toBoolean) }
                .forEachIndexed { y, bl ->
                    bl.forEachIndexed {x, b ->
                        result.put(Coordinate(x,y), b)
                    }
                }
    }


    fun match(grid: Map<Coordinate, Boolean>): Boolean {
        return cases.any { match(it, grid) }
    }

    private fun match(rule: Map<Coordinate, Boolean>, grid: Map<Coordinate, Boolean>): Boolean {
        rule.keys.forEach {
            if(grid.getOrDefault(it, false) != rule.getValue(it)) {
                return false
            }
        }
        return true
    }
}

class Day21: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {
        var grid = mutableMapOf<Coordinate, Boolean>()
        grid.put(Coordinate(0,0), false)
        grid.put(Coordinate(1,0), true)
        grid.put(Coordinate(2, 0), false)

        grid.put(Coordinate(0,1), false)
        grid.put(Coordinate(1,1), false)
        grid.put(Coordinate(2,1), true)

        grid.put(Coordinate(0,2), true)
        grid.put(Coordinate(1,2), true)
        grid.put(Coordinate(2,2), true)

        val rules = input.map {
            val (from, to) = it.split("=>").map(String::trim)
            Rule(from, to)
        }

        val rulesEven = rules.filter { it.size == 2 }
        val rulesOdd = rules.filter { it.size == 3 }
        var iterations = 0

        val goal = if(!partTwo) {
            5
        } else {
            18
        }


        while (true) {
            if(iterations == goal) {
                break
            }

            grid = if(determineSize(grid) % 2 == 0) {
                enhance(2, grid, rulesEven)
            } else {
                enhance(3, grid, rulesOdd)
            }


            iterations++
        }

        return grid.values.filter { it }.size.toString()
    }

    private fun enhance(step: Int, grid: Map<Coordinate, Boolean>, rules: List<Rule>): MutableMap<Coordinate, Boolean> {
        val newGrid = mutableMapOf<Coordinate, Map<Coordinate, Boolean>>()
        val size = determineSize(grid)

        val sides = size / step
        val squareGrids = mutableMapOf<Coordinate, Map<Coordinate, Boolean>>()

        for (s1 in 0 until sides) {
            for (s2 in 0 until sides) {
                val sGrid = mutableMapOf<Coordinate, Boolean>()
                for (y in 0 until step) {
                    for (x in 0 until step) {
                        val readCoordinate = Coordinate(x + s1 * step, y + s2 * step)
                        readCoordinate.x
                        readCoordinate.y
                        sGrid.put(Coordinate(x, y), grid.getOrDefault(readCoordinate, false))
                    }
                }
                squareGrids.put(Coordinate(s1*step, s2*step), sGrid)
            }
        }

        squareGrids.forEach { c, g ->
            for(r in rules) {
                if(r.match(g)) {
                    newGrid.put(c, r.result)
                    break
                }
            }
        }

        val retMap = mutableMapOf<Coordinate, Boolean>()
        val gridsPerRow = Math.sqrt(newGrid.size.toDouble()).toInt()
        val rows = newGrid.size / gridsPerRow
        val newSize = determineSize(newGrid.values.first())

        var writeX = 0
        var writeY = 0
        for (r in 0 until rows) {
            for (y in 0 until newSize) {
                for (g in 0 until gridsPerRow) {
                    val grid = newGrid.getValue(Coordinate(step*g, step*r))
                    for (x in 0 until newSize) {
                        val value = grid.getValue(Coordinate(x, y))
                        retMap.put(Coordinate(writeX, writeY), value)
                        writeX++
                    }
                }
                writeY++
                writeX = 0
            }
        }

        return retMap
    }



}
