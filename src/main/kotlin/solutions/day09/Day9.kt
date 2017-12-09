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

        while(iterator.hasNext()) {
            val c = iterator.nextChar()
            if(c == '<') {
                garbageSum += handleGarbage(iterator)
            }

            when(c) {
                '{' -> curr = goDeeper(curr)
                '}' -> curr = goShallower(curr)
            }
        }

        return if(!partTwo) {
            sumGroupScore(root).toString()
        } else {
            garbageSum.toString()
        }
    }

    private fun goShallower(curr: Node): Node {
        return curr.parent ?: curr
    }

    private fun goDeeper(curr: Node): Node {
        val deeperNode = Node(parent = curr, children = mutableListOf())
        curr.addChild(deeperNode)
        return deeperNode
    }

    private fun handleGarbage(iterator: CharIterator): Int {
        var garbageSum = 0
        while(iterator.hasNext()) {
            val c = iterator.nextChar()
            when(c) {
                '!' -> iterator.next()
                '>' -> return garbageSum
                else -> garbageSum++
            }
        }
        throw RuntimeException("Unexpected no end of garbage")
    }

    private fun sumGroupScore(root: Node, level: Int = 1): Int {
        return level + root.children.map { sumGroupScore(it, level+1) }.sum()
    }
}