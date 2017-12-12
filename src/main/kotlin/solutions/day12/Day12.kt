
package solutions.day12

import solutions.Solver

data class Node(val id: Int, val neighbours: MutableSet<Node>) {
    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return when(other) {
            is Node -> id == other.id
            else -> false
        }
    }
}

class Day12: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {
        val nodes = mutableMapOf<Int, Node>()
        buildGraphs(input, nodes)

        val nodesConnected = traverse(nodes.getValue(0))
        if(!partTwo) {
            return nodesConnected.size.toString()
        }

        val groups = mutableListOf(nodesConnected)

        var lonelyNode = findUnconnectedNode(nodes, groups)
        while (lonelyNode != null) {
            val newGroup = traverse(lonelyNode)
            groups.add(newGroup)

            lonelyNode = findUnconnectedNode(nodes, groups)
        }

        return groups.size.toString()
    }

    private fun findUnconnectedNode(nodes: Map<Int, Node>, groups: List<Set<Node>>): Node? {
        return nodes.values.firstOrNull {
            n -> groups.none { g -> g.contains(n) }
        }
    }

    private fun buildGraphs(input: List<String>, nodes: MutableMap<Int, Node>) {
        input.map { it.split("<->") }
                .forEach {
                    val id = it[0].trim().toInt()
                    val neighbours = it[1].split(",").map(String::trim).map(String::toInt)

                    val node = nodes.computeIfAbsent(
                            id,
                            { nodeId -> Node(nodeId, mutableSetOf()) }
                    )

                    connect(node, neighbours, nodes)
                }
    }

    private fun traverse(value: Node, nodes: MutableSet<Node> = mutableSetOf()): Set<Node> {
        nodes.add(value)
        value.neighbours
                .filter { !nodes.contains(it) }
                .forEach { traverse(it, nodes) }

        return nodes
    }

    private fun connect(node: Node, neighbours: List<Int>, nodes: MutableMap<Int, Node>) {
        neighbours.forEach {
            val neighbourNode = nodes.computeIfAbsent(
                    it,
                    {id -> Node(id, mutableSetOf()) }
            )
            node.neighbours.add(neighbourNode)
            neighbourNode.neighbours.add(node)
        }
    }
}
