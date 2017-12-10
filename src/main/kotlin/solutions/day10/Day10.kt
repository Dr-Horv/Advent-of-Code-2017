package solutions.day10

import solutions.Solver

class Day10: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {

        val lengths = if(!partTwo) {
            input.first().split(",")
                    .map(String::trim)
                    .map(String::toInt)
        } else {
            val inputLengths = input.first().toCharArray().map(Char::toInt).toMutableList()
            inputLengths.addAll(listOf(17, 31, 73, 47, 23))
            inputLengths.toList()
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

        val knotHasher = KnotHasher(list=numbers.toList(), lengths = lengths)
        for(i in 1..rounds) {
            knotHasher.doRound()
        }

        val list = knotHasher.getList()

        return if(!partTwo) {
            (list[0] * list[1]).toString()
        } else {
            calculateDenseHash(list)
        }

    }

    private fun calculateDenseHash(list: List<Int>): String =
            list.chunked(16)
                .map { it.reduce({ acc, curr -> acc xor curr }) }
                .map(Int::toHexString)
                .reduce { acc, s -> acc+s }
}

private fun Int.toHexString(): String {
    val hexString = java.lang.Integer.toHexString(this)
    return if(hexString.length == 1) {
        "0$hexString"
    } else {
        hexString
    }
}
