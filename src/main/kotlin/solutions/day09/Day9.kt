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
        val stream = first.toCharArray().slice(IntRange(1, first.lastIndex - 1)) // Create outmost node before loop
        val iterator = stream.toCharArray().iterator()
        var curr = Node(null, mutableListOf())
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
            sumGroupScore(curr).toString()
        } else {
            garbageSum.toString()
        }
    }

    private fun goShallower(curr: Node): Node {
        if(curr.parent != null) {
            return curr.parent
        } else {
            throw RuntimeException("Unexpected no parent")
        }
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