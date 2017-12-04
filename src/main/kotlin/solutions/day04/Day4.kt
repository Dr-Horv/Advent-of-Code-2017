package solutions.day04

import solutions.Solver
import utils.splitAtWhitespace

class Day4: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {

        val validator = if(!partTwo) {
            this::isValidPassphrase
        } else {
            this::hasNoAnagrams
        }

        return input
                .map(::splitAtWhitespace)
                .filter(validator)
                .size
                .toString()

    }

    private fun isValidPassphrase(words: List<String>): Boolean {
        val sortedWords = words.sorted()
        return anyEqualConsecutiveWord(sortedWords)
    }

    private fun hasNoAnagrams(words: List<String>): Boolean {
        val sortedWords = words
                .map { it.toCharArray().sorted().toString() }
                .sorted()
        return anyEqualConsecutiveWord(sortedWords)
    }

    private fun anyEqualConsecutiveWord(sortedWords: List<String>): Boolean {
        return (0 until sortedWords.lastIndex).none { sortedWords[it] == sortedWords[it + 1] }
    }


}