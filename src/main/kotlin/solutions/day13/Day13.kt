package solutions.day13

import solutions.Solver


data class Scanner(val depth: Int, val range: Int) {
    fun positionAt(t: Int) = t % ((range-1) * 2)
    fun caught(t: Int) = positionAt(t) == 0
    fun severity() = depth * range

}

class Day13 : Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {

        val scanners = input.map {
            val (depth, range) = it.split(":").map(String::trim)
            Scanner(depth.toInt(), range.toInt())
        }

        if(!partTwo) {
            return doRun(scanners, 0).sum().toString()
        }

        var start = 0
        while (true) {
            val severities = doRun(scanners, start)
            if(severities.isEmpty()) {
                return start.toString()
            }
            start++
        }
    }

    private fun doRun(scanners: List<Scanner>, start: Int): MutableList<Int> {
        val severities = mutableListOf<Int>()
        scanners.forEach { scanner ->
            if (scanner.caught(start + scanner.depth)) {
                severities.add(scanner.severity())
            }
        }
        return severities
    }

}
