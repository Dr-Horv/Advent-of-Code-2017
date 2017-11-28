package utils

import org.hamcrest.CoreMatchers.`is` as isEqual
import org.hamcrest.CoreMatchers.anyOf
import org.junit.Assert.*
import org.junit.Test

data class Node(val x: Int, val y: Int)

fun Int.distance(other: Int) = Math.abs(this - other)

class MapExpander(val start: Node) : NodeExpander<Node> {
    /**
     * S E E
     * E B E
     * E E G
     *
     * S = start
     * E = empty
     * B = blocked
     * G = goal
     *
     */

    override fun estimateCost(node: Node): Double = (start.x.distance(node.x) + start.y.distance(node.y)).toDouble()

    override fun expand(node: Node): Collection<Node> = when (node) {
        Node(0,0) -> listOf(Node(1,0), Node(0,1))
        Node(1,0) -> listOf(Node(2,0), Node(0,0))
        Node(0,1) -> listOf(Node(0, 2), Node(0, 0))
        Node(2,0) -> listOf(Node(1,0), Node(2,1))
        Node(0, 2) -> listOf(Node(0, 1), Node(1,2))
        Node(2, 1) -> listOf(Node(2,0), Node(2,2))
        Node(1, 2) -> listOf(Node(0, 2), Node(2,2))
        else -> throw RuntimeException("Mapper not mapped for $node" )
    }

}

class AStarTest {

    @Test
    fun searchTest() {

        val start = Node(0,0)
        val search = search(start, { it == Node(2, 2) }, MapExpander(start))

        assertNotNull(search)
        assertEquals(5, search!!.size)
        assertThat(search, anyOf(
                isEqual(listOf(Node(0,0), Node(1,0), Node(2,0), Node(2,1), Node(2,2))),
                isEqual(listOf(Node(0,0), Node(0,1), Node(0,2), Node(1,2), Node(2,2)))
        ))

    }
}