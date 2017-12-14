
package solutions.day14

import solutions.Solver
import utils.Coordinate
import utils.Direction
import utils.KnotHasher
import utils.step

fun Long.toBinaryString(): String {
    val s = java.lang.Long.toBinaryString(this)
    return if(s.length < 4) {
        s.padStart(4, '0')
    } else {
        s
    }
}

class Day14: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {
        val key = input.first()
        val map = mutableMapOf<Coordinate, Boolean>()
                .withDefault { false }

        for (row in 0 until 128) {
            val hashKey = "$key-$row"
            val hasher = KnotHasher(lengths = KnotHasher.lengthsFromString(hashKey))
            for (round in 1..64) {
                hasher.doRound()
            }
            val denseHash = hasher.calculateDenseHash()
            var column = 0
            for (hex in denseHash) {
                val binaryString = hex.toString().toLong(16).toBinaryString()
                for(c in binaryString) {
                    when(c) {
                        '1' ->  {
                            map[Coordinate(column, row)] = true

                        }

                    }
                    column++
                }
            }
        }

        if(!partTwo) {
            return map.values.size.toString()
        }

        val regions = mutableListOf<Set<Coordinate>>()
        map.entries
                .filter { it.value }
                .forEach {
                    if(hasNoRegion(it.key, regions)) {
                        regions.add(traverseRegion(it.key, map))
                    }
                }

        return regions.size.toString()


    }

    private fun traverseRegion(key: Coordinate, map: Map<Coordinate, Boolean>, region:MutableSet<Coordinate> = mutableSetOf()): Set<Coordinate> {
        val used = map.getValue(key);
        if(used) {
            region.add(key)
            Direction.values()
                    .forEach {
                        val next = key.step(it)
                        if(!region.contains(next)) {
                            traverseRegion(next, map, region)
                        }
                    }

        }
        return region
    }

    private fun hasNoRegion(key: Coordinate, regions: MutableList<Set<Coordinate>>): Boolean {
        return regions.none { it.contains(key) }
    }
}
