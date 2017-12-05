package solutions.day05

import solutions.Solver

class Day5: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {

        val instructions: MutableList<Int> = input.map(String::toInt).toMutableList()
        var index = 0
        var steps = 0

        while (true) {
            if(index < 0 || index > instructions.lastIndex) {
                return steps.toString()
            }
            val curr = instructions[index]

            val update = if(partTwo && curr >= 3) {
                -1
            } else {
                1
            }

            instructions[index] += update
            index += curr
            steps++
        }

    }

}