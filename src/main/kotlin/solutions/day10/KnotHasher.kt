package solutions.day10

class KnotHasher (
        private var list: List<Int>,
        private var lengths: List<Int>) {

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