
package solutions.day07

import solutions.Solver

import utils.splitAtWhitespace
import java.util.*

data class Program(val name: String, val weight: Int, var parent: Program?, var children: List<Program>, var totalWeight: Int, var level: Int)
data class Tuple(val program: Program, val diff: Int)

class LevelComparator: Comparator<Program> {
    override fun compare(p0: Program?, p1: Program?): Int {
        return p1!!.level - p0!!.level
    }
}

class Day7: Solver {


    override fun solve(input: List<String>, partTwo: Boolean): String {
        val map: MutableMap<String, Program> = mutableMapOf()
        val programsWithChildren : MutableMap<Program, List<String>> = mutableMapOf()
        val parentsMap: MutableMap<String, Program> = mutableMapOf()

        input
                .map { it.split("->").map { it.trim() } }
                .forEach {
                    val firstParts = it[0].splitAtWhitespace()
                    val name = firstParts[0]
                    val weightStr = firstParts[1]
                    val weight = weightStr.substring(1, weightStr.lastIndex).toInt()
                    val p = Program(name, weight, null, mutableListOf(), weight, -1)
                    map.put(name, p)
                    if(it.size > 1) {
                        val children = it[1].split(",").map(String::trim)
                        programsWithChildren.put(p, children)
                        children.forEach { parentsMap.put(it, p) }
                    }
                }

        programsWithChildren.forEach {(p,cs) ->
            val children = cs.map { map.getValue(it) }.toList()
            p.children = children
            children.forEach { it.parent = p }
        }

        val k = map.keys.first()

        val firstProgram = map.getValue(k)

        val root = findRoot(firstProgram)

        if(!partTwo) {
            return root.name
        }

        correctLevels(root)

        val queue : PriorityQueue<Program> = PriorityQueue(LevelComparator()) //map.values.filter({it.children.isEmpty()}).toCollection(LinkedList())


        queue.addAll(map.values)

        var sanity = 0
        while (queue.isNotEmpty()) {
            sanity++
            val next = queue.poll()
            if(next.parent != null) {
                val parent = next.parent!!
                parent.totalWeight += next.totalWeight
                if(!queue.any { p -> p.name == parent.name }){
                    queue.add(parent)
                }
            }
        }
        
        val (p, diff) = findCulprit(root)

        return (p.weight + diff).toString()

    }

    private fun correctLevels(root: Program, level:Int = 0) {
        root.level = level
        root.children.forEach { correctLevels(it, level+1) }
    }

    private fun findCulprit(root: Program): Tuple {
        val weightSorted = root.children.sortedBy { it.totalWeight }

        if(weightSorted.size < 3) {
            throw RuntimeException("Cannot determine too heavy or too light")
        }

        val first = weightSorted.first()
        val last = weightSorted.last()
        val middle = weightSorted[weightSorted.size/2]

        return if(first.totalWeight != middle.totalWeight) {
            findLighter(first, first.totalWeight - middle.totalWeight)
        } else {
            findHeavier(last, middle.totalWeight - last.totalWeight)
        }


    }

    private fun findHeavier(p: Program, diff: Int): Tuple {
        val weightSorted = p.children.sortedBy { it.totalWeight }
        return if(weightSorted.first().totalWeight == weightSorted.last().totalWeight) {
            Tuple(p, diff)
        } else {
            findHeavier(weightSorted.last(), diff)
        }
    }

    private fun findLighter(p: Program, diff: Int): Tuple {
        val weightSorted = p.children.sortedBy { it.totalWeight }
        return if(weightSorted.first().totalWeight == weightSorted.last().totalWeight) {
            Tuple(p, diff)
        } else {
            findLighter(weightSorted.first(), diff)
        }
    }

    private fun addWeightUpwards(p: Program, w: Int) {
        p.totalWeight += w
        if(p.parent != null) {
            addWeightUpwards(p.parent!!, w)
        }

    }

    private fun findRoot(program: Program): Program {
        var curr = program
        return if(curr.parent != null) {
            findRoot(curr.parent!!)
        } else {
            curr
        }
    }
}
