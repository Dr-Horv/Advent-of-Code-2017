package solutions.day13

import solutions.Solver

enum class Direction {
    UP,
    DOWN
}

data class Scanner(val depth: Int, val range: Int, var position: Int = 0, var direction: Direction = Direction.DOWN) {
    fun move() {
        when {
            position == 0 && direction == Direction.UP -> direction = Direction.DOWN
            position == (range-1) && direction == Direction.DOWN -> direction = Direction.UP
        }

        when(direction) {
            Direction.UP -> position--
            Direction.DOWN -> position++
        }

    }
}

class Day13 : Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {

        val initialStateOfScanners = input.map { it.split(":").map(String::trim).map(String::toInt) }
                .fold(mutableMapOf<Int, Scanner>(), { acc, sd ->
                    val depth = sd[0]
                    acc.put(depth, Scanner(depth, sd[1]))
                    acc
                }).toMap()


        val goal = initialStateOfScanners.values.maxBy { it.depth }!!.depth+1
        if(!partTwo) {
            return doRun2(goal, copyScannerMap(initialStateOfScanners)).first.toString()
        }

        var delay = 0
        while (true) {
            val scanners = copyScannerMap(initialStateOfScanners)
            scanners.values.forEach {
                for(i in 0 until delay) {
                    it.move()
                }
            }

            val (_, timesCaught) = doRun2(goal, scanners)
            if(timesCaught == 0) {
                return delay.toString()
            }
            delay++
        }
    }

    private fun doRun(goal: Int, scannersMap: Map<Int, Scanner>): Pair<Int, Int> {
        var position = 0
        var severity = 0
        var timesCaught = 0
        while (position != goal) {
            val potentialScanner = scannersMap[position]
            if (potentialScanner != null) {
                if (potentialScanner.position == 0) {
                    severity += (potentialScanner.depth * potentialScanner.range)
                    timesCaught++
                }
            }

            scannersMap.values.forEach(Scanner::move)
            position++
        }

        return Pair(severity, timesCaught)
    }

    private fun doRun2(goal: Int, scannersMap: Map<Int, Scanner>): Pair<Int, Int> {
        return scannersMap.values.map {
            val caught = it.depth % (it.range*2)  == 0
            if(caught) {
                Pair(it.depth * it.range, 1)
            } else {
                Pair(0, 0)
            }
        }.reduce { acc, pair -> Pair(acc.first+pair.first, acc.second+pair.second) }
    }

    private fun copyScannerMap(map: Map<Int, Scanner>): Map<Int, Scanner> {
        val newMap = mutableMapOf<Int, Scanner>()
        map.entries.forEach { newMap.put(it.key, it.value.copy()) }
        return newMap.toMap()
    }
}
