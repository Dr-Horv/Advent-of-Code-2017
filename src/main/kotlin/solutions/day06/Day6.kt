package solutions.day06

import solutions.Solver

import utils.splitAtWhitespace

data class Tuple(val index: Int, val value: Int)

class Day6 : Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {
        val banks = input.first()
                .splitAtWhitespace()
                .map(String::toInt)
                .toTypedArray()

        var steps = 0
        val history = LinkedHashSet<List<Int>>()
        history.add(banks.copyOf().toList())

        while (true) {
            val (index, value) = banks.foldIndexed(Tuple(-1, -1), this::findFullestBank)

            val blocks = banks[index]
            banks[index] = 0

            var currentIndex = index
            for (b in 1..blocks) {
                currentIndex = (currentIndex + 1) % banks.size
                banks[currentIndex]++
            }

            steps++

            val copiedBanks = banks.copyOf().toList();
            if (history.contains(copiedBanks)) {
                return if(!partTwo) {
                    steps.toString()
                } else {
                    (steps - history.indexOf(copiedBanks)).toString()
                }
            } else {
                history.add(copiedBanks)
            }
        }

    }

    private fun findFullestBank(index: Int, acc: Tuple, curr: Int): Tuple {
        return if (curr > acc.value) {
            Tuple(index, curr)
        } else if (curr == acc.value && index < acc.index) {
            Tuple(index, curr)
        } else {
            acc
        }
    }
}
