
package solutions.day24

import solutions.Solver

data class Component(val port1: Int, val port2: Int) {
    override fun toString(): String {
        return "$port1/$port2"
    }
}

fun List<Component>.strength(): Int = this.sumBy { it.port1 + it.port2 }

class Day24: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {
        val components = input.map {
            val (port1, port2) = it.split("/")
            Component(port1.toInt(), port2.toInt())
        }

        var maxStrength = Int.MIN_VALUE
        var strongestLongest = listOf<Component>()
        components.filter { it.port1 == 0 || it.port2 == 0 }
                .forEach {
                    val isPort1 = it.port1 == 0

                    val bridges = doBridge(it, isPort1, components.filter { c -> c != it })

                    bridges.forEach {
                        val strength = it.strength()
                        if(strength > maxStrength) {
                            maxStrength = strength
                        }
                    }

                    if(partTwo) {
                        val sortedBy = bridges.sortedBy(List<Component>::size).reversed()

                        println(sortedBy.first().size)

                        val candidate = sortedBy.filter { it.size == sortedBy.first().size }
                                .reduce { acc, list ->
                                    if (list.strength() > acc.strength()) {
                                        list
                                    } else {
                                        acc
                                    }

                                }

                        when {
                            candidate.size > strongestLongest.size -> strongestLongest = candidate
                            candidate.size == strongestLongest.size
                                    && candidate.strength() > strongestLongest.strength() -> strongestLongest = candidate
                        }

                    }
                }

        return if(!partTwo) {
            maxStrength.toString()
        } else {
            strongestLongest.strength().toString()
        }


    }

    private fun doBridge(start: Component, isPort1: Boolean, rest: List<Component>, currentBridge: List<Component> = listOf()): List<List<Component>> {
        val bridges = mutableListOf<List<Component>>()
        val newCurrentBridge = currentBridge.toMutableList()
        newCurrentBridge.add(start)
        bridges.add(newCurrentBridge)

        val filterRule = if(isPort1) {
            {c: Component -> c.port1 == start.port2 || c.port2 == start.port2 }
        } else {
            {c: Component -> c.port1 == start.port1 || c.port2 == start.port1 }
        }

        val possibleComponents = rest.filter(filterRule)

        possibleComponents.forEach {
            val newIsPort1 = if(isPort1) { it.port1 == start.port2 } else { it.port1 == start.port1 }
            val newBridges = doBridge(it, newIsPort1, rest.filter { c -> c != it }, newCurrentBridge)
            bridges.addAll(newBridges)
        }

        return bridges
    }
}
