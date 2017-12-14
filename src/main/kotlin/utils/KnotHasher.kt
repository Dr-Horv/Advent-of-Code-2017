package utils


private fun Int.toHexString(): String {
    val hexString = java.lang.Integer.toHexString(this)
    return if(hexString.length == 1) {
        "0$hexString"
    } else {
        hexString
    }
}

class KnotHasher (
        private var lengths: List<Int>,
        private var list: List<Int> = (0..255).toList()) {

    companion object {
        fun lengthsFromString(input: String): List<Int> {
            val inputLengths = input.toCharArray().map(Char::toInt).toMutableList()
            inputLengths.addAll(listOf(17, 31, 73, 47, 23))
            return inputLengths.toList()
        }
    }

    private var currentPosition: Int = 0
    private var skipSize: Int = 0

    fun getList(): List<Int> = list.toList()

    fun doRound() {
        lengths.forEach {
            list = reverse(list, currentPosition, it)
            currentPosition = (currentPosition + it + skipSize) % list.size
            skipSize++
        }
    }

    fun calculateDenseHash(): String =
            list.chunked(16)
                    .map { it.reduce({ acc, curr -> acc xor curr }) }
                    .map(Int::toHexString)
                    .reduce { acc, s -> acc+s }

    private fun reverse(list: List<Int>, index:Int, length: Int): List<Int> {
        val mutableList = list.toMutableList()
        for(i in 0 until length/2) {
            val index1 = (index + i) % mutableList.size
            val index2 = (index + length-1 - i) % mutableList.size
            val value = mutableList[index1]
            mutableList[index1] = mutableList[index2]
            mutableList[index2] = value
        }

        return mutableList

    }
}