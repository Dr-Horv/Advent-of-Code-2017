package solutions.day01

import solutions.Solver

class Day1 : Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {
        val line1 = input.first()
        val sum = line1.indices
                .filter { isValid(it, line1, partTwo) }
                .sumBy { line1[it].toString().toInt() }

        return sum.toString()
    }



    private fun isValid(i: Int, input: String, partTwo: Boolean): Boolean {
        val steps = if(partTwo) {
            input.length / 2
        } else {
            1
        }
        val next = getNext(i, input, steps)
        val curr = input[i]
        return curr == next
    }

    private fun getNext(i: Int, input: String, steps: Int): Char {
        val nextIndex = (i + steps) % input.length
        return input[nextIndex]
    }
}