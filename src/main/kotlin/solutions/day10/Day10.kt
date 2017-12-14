package solutions.day10

import solutions.Solver
import utils.KnotHasher

class Day10: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {

        val lengths = if(!partTwo) {
            input.first().split(",")
                    .map(String::trim)
                    .map(String::toInt)
        } else {
            KnotHasher.lengthsFromString(input.first())
        }

        val numbers = if(lengths.size == 4) {
            0..4
        } else {
            0..255
        }

        val rounds = if(!partTwo) {
            1
        } else {
            64
        }

        val knotHasher = KnotHasher(lengths = lengths, list = numbers.toList())
        for(i in 1..rounds) {
            knotHasher.doRound()
        }

        val list = knotHasher.getList()

        return if(!partTwo) {
            (list[0] * list[1]).toString()
        } else {
            knotHasher.calculateDenseHash()
        }

    }

}

