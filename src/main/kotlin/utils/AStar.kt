package utils

interface NodeExpander<N> {
    fun estimateCost(node: N) : Double
    fun expand(node: N) : Collection<N>
}

fun <N> search(start: N, isGoal: (node: N) -> Boolean, expander: NodeExpander<N>): List<N>? {

    val closedSet: MutableCollection<N> = mutableSetOf()

    // TODO change this to priority queue
    val openSet: MutableCollection<N> = mutableSetOf(start)

    val cameFrom: MutableMap<N, N> = HashMap()
    val gScore: MutableMap<N, Double> = mutableMapOf<N, Double>().withDefault { Double.POSITIVE_INFINITY }
    gScore.put(start, 0.0)

    val fScore: MutableMap<N, Double> = mutableMapOf<N, Double>().withDefault { Double.POSITIVE_INFINITY }
    fScore.put(start, expander.estimateCost(start))

    while (openSet.isNotEmpty()) {
        val current = openSet.minBy { fScore.getValue(it) }!!
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
    val totalPath = mutableListOf<N>(start)
    var current = start
    while (cameFrom.containsKey(current)) {
        current = cameFrom.getValue(current)
        totalPath.add(current)
    }

    return totalPath
}
