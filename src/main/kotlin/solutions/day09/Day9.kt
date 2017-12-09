package solutions.day09

import solutions.Solver

data class Node(val parent: Node?, val children: MutableList<Node>) {
    fun addChild(child: Node) {
        children.add(child)
    }
}

class Day9: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {

        val first = input.first()
        val stream = first.toCharArray().slice(IntRange(1, first.lastIndex))
        val iterator = stream.toCharArray().iterator()

        val root = Node(null, mutableListOf())
        var curr = root
        var garbageSum = 0
        var inGarbage = false

        while(iterator.hasNext()) {
            val c = iterator.nextChar()
            if(inGarbage) {
                when(c) {
                    '!' -> iterator.next()
                    '>' -> inGarbage = false
                    else -> garbageSum++
                }
                continue
            }

            when(c) {
                '{' -> {
                    val deeperNode = Node(parent = curr, children = mutableListOf())
                    curr.addChild(deeperNode)
                    curr = deeperNode
                }
                '}' -> {
                    if(curr.parent != null) {
                        curr = curr.parent!!
                    }
                }
                '<' -> inGarbage = true
            }
        }

        return if(!partTwo) {
            sumGroupScore(root).toString()
        } else {
            garbageSum.toString()
        }
    }

    private fun sumGroupScore(root: Node, level: Int = 1): Int {
        return level + root.children.map { sumGroupScore(it, level+1) }.sum()
    }
}