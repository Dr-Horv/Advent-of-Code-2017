package utils

import java.util.*

interface NodeExpander<N> {
    fun estimateCost(node: N) : Double
    fun expand(node: N) : Collection<N>
}

fun <N> search(start: N, isGoal: (node: N) -> Boolean, expander: NodeExpander<N>): List<N>? {

    val closedSet: MutableCollection<N> = mutableSetOf()

    val cameFrom: MutableMap<N, N> = HashMap()
    val gScore: MutableMap<N, Double> = mutableMapOf<N, Double>().withDefault { Double.POSITIVE_INFINITY }
    gScore.put(start, 0.0)

    val fScore: MutableMap<N, Double> = mutableMapOf<N, Double>().withDefault { Double.POSITIVE_INFINITY }
    fScore.put(start, expander.estimateCost(start))
    val openSet: PriorityQueue<N> = PriorityQueue({e1,e2 -> java.lang.Double.compare(fScore.getValue(e1), fScore.getValue(e2))})
    openSet.add(start)

    var iteration = 0
    while (openSet.isNotEmpty()) {
        if(iteration % 1000 == 0) {
            println("$iteration Open: ${openSet.size} Closed: ${closedSet.size}")
        }

        iteration++
        val current = openSet.poll()!!

        if (isGoal(current)) {
            return reconstructPath(cameFrom, current)
        }

        openSet.remove(current)
        closedSet.add(current)

        for (neighbour: N in expander.expand(current)) {
            if(closedSet.contains(neighbour)) {
                continue
            }

            if(!openSet.contains(neighbour)) {
                openSet.add(neighbour)
            }

            val tentativeGScore = gScore.getValue(current) + distanceBetween(current, neighbour)

            if(tentativeGScore >= gScore.getValue(neighbour)) {
                continue
            }

            cameFrom.put(neighbour, current)
            gScore.put(neighbour, tentativeGScore)
            fScore.put(neighbour, tentativeGScore + expander.estimateCost(neighbour))
        }
    }

    return null
}

fun <N> distanceBetween(current: N, neighbour: N): Int = 1

fun <N> reconstructPath(cameFrom: Map<N, N>, start: N): List<N> {
    val totalPath = mutableListOf(start)
    var current = start
    while (cameFrom.containsKey(current)) {
        current = cameFrom.getValue(current)
        totalPath.add(current)
    }

    return totalPath.reversed()
}
