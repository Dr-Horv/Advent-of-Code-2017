package solutions.day02

import solutions.Solver

class Day2: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {
        val rowMapper = if(partTwo) {
            this::evenlyDivisible
        } else {
            this::largestDifference
        }

        return input
                .map(this::toNumbers)
                .map(rowMapper)
                .sum()
                .toString()
    }

    private fun toNumbers(row: String): List<Int> {
        return row.trim().split(Regex("\\s+")).map(String::toInt)
    }

    private fun largestDifference(row: List<Int>): Int {
        var largest = Int.MIN_VALUE
        var smallest = Int.MAX_VALUE
        for (i in row) {
            if(i > largest) {
                largest = i
            }
            if(i < smallest) {
                smallest = i
            }
        }

        return largest - smallest
    }


    private fun evenlyDivisible(row: List<Int>): Int {
        for (index1 in row.indices) {
            val i = row[index1]
            if(i == 0) {
                continue
            }
            for (index2 in index1..row.lastIndex) {
                val j = row[index2]
                if(j == 0 || i == j) {
                    continue
                }
                if(i % j == 0) {
                    return i / j
                }
                if(j % i == 0) {
                    return j / i
                }
            }
        }
        throw RuntimeException("Found no evenly divisible numbers")
    }

}