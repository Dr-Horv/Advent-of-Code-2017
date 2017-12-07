package solutions.day07

import solutions.Solver

import utils.splitAtWhitespace
import java.util.*

data class Program(val name: String, val weight: Int, var parent: Program?, var children: List<Program>, var totalWeight: Int, var level: Int)
data class Tuple(val program: Program, val diff: Int)

class LevelComparator : Comparator<Program> {
    override fun compare(p0: Program?, p1: Program?): Int {
        return p1!!.level - p0!!.level
    }
}

class Day7 : Solver {


    override fun solve(input: List<String>, partTwo: Boolean): String {
        val map: Map<String, Program> = constructTree(input)
        val root = findRoot(map.values.first())
        if (!partTwo) {
            return root.name
        }

        correctLevels(root)
        correctTotalWeights(map)

        val (p, diff) = findCulprit(root)

        return (p.weight + diff).toString()

    }

    private fun correctTotalWeights(map: Map<String, Program>) {
        val queue: Queue<Program> = PriorityQueue(LevelComparator())
        queue.addAll(map.values)

        while (queue.isNotEmpty()) {
            val next = queue.poll()
            if (next.parent != null) {
                val parent = next.parent!!
                parent.totalWeight += next.totalWeight
                if (!queue.contains(parent)) {
                    queue.add(parent)
                }
            }
        }
    }

    private fun constructTree(input: List<String>): Map<String, Program> {
        val map: MutableMap<String, Program> = mutableMapOf()
        val programsWithChildren: MutableMap<Program, List<String>> = mutableMapOf()
        input
                .map { it.split("->").map(String::trim) }
                .forEach {
                    createProgramNode(it, map, programsWithChildren)
                }

        programsWithChildren.forEach { (p, cs) ->
            val children = cs.map { map.getValue(it) }.toList()
            p.children = children
            children.forEach { it.parent = p }
        }

        return map
    }

    private fun createProgramNode(parts: List<String>, map: MutableMap<String, Program>, programsWithChildren: MutableMap<Program, List<String>>) {
        val firstParts = parts[0].splitAtWhitespace()
        val name = firstParts[0]
        val weightStr = firstParts[1]
        val weight = weightStr.substring(1, weightStr.lastIndex).toInt()
        val p = Program(name, weight, null, mutableListOf(), weight, -1)
        map.put(name, p)
        if (parts.size > 1) {
            val children = parts[1].split(",").map(String::trim)
            programsWithChildren.put(p, children)

        }
    }

    private fun correctLevels(root: Program, level: Int = 0) {
        root.level = level
        root.children.forEach { correctLevels(it, level + 1) }
    }

    private fun findCulprit(root: Program): Tuple {
        val weightSorted = root.children.sortedBy { it.totalWeight }

        if (weightSorted.size < 3) {
            throw RuntimeException("Cannot determine too heavy or too light")
        }

        val first = weightSorted.first()
        val last = weightSorted.last()
        val middle = weightSorted[weightSorted.size / 2]

        return if (first.totalWeight != middle.totalWeight) {
            findLighter(first, first.totalWeight - middle.totalWeight)
        } else {
            findHeavier(last, middle.totalWeight - last.totalWeight)
        }


    }

    private fun findHeavier(p: Program, diff: Int): Tuple {
        val weightSorted = p.children.sortedBy { it.totalWeight }
        return if (weightSorted.first().totalWeight == weightSorted.last().totalWeight) {
            Tuple(p, diff)
        } else {
            findHeavier(weightSorted.last(), diff)
        }
    }

    private fun findLighter(p: Program, diff: Int): Tuple {
        val weightSorted = p.children.sortedBy { it.totalWeight }
        return if (weightSorted.first().totalWeight == weightSorted.last().totalWeight) {
            Tuple(p, diff)
        } else {
            findLighter(weightSorted.first(), diff)
        }
    }

    private fun findRoot(program: Program): Program {
        return if (program.parent != null) {
            findRoot(program.parent!!)
        } else {
            program
        }
    }
}
