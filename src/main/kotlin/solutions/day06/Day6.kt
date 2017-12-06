package solutions.day06

import solutions.Solver

import utils.splitAtWhitespace

data class Tuple(val index: Int, val value: Int)

class Day6 : Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {
        val banks = input.first()
                .splitAtWhitespace()
                .map(String::toInt)
                .toMutableList()

        var steps = 0
        val history = LinkedHashSet<List<Int>>()
        history.add(banks.toList())

        while (true) {
            redistribute(banks)
            steps++
            val copiedBanks = banks.toList()
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

    private fun redistribute(banks: MutableList<Int>) {
        val (index, blocks) = banks.foldIndexed(Tuple(-1, -1), this::findFullestBank)
        banks[index] = 0

        var currentIndex = index
        for (b in 1..blocks) {
            currentIndex = (currentIndex + 1) % banks.size
            banks[currentIndex]++
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
