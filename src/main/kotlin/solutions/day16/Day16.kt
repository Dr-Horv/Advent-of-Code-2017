
package solutions.day16

import solutions.Solver

class TwoWayMap<K, V> {
    private val keyMap = mutableMapOf<K, V>()
    private val valueMap = mutableMapOf<V, K>()

    fun put(key: K, value: V) {
        keyMap.put(key, value)
        valueMap.put(value, key)
    }

    fun putAll(map: TwoWayMap<K, V>) {
        map.entries().forEach {
            put(it.key, it.value)
        }
    }

    fun entries(): MutableSet<MutableMap.MutableEntry<K, V>> {
        return keyMap.entries
    }

    fun size(): Int {
        return keyMap.size
    }

    fun getValue(key: K): V {
        return keyMap.getValue(key)
    }

    fun getKey(value: V): K {
        return valueMap.getValue(value)
    }

    fun copy(): TwoWayMap<K, V> {
        val newMap = TwoWayMap<K, V>()
        keyMap.forEach { k, v -> newMap.put(k, v) }
        return newMap
    }
}

class Day16: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {

        val symbolTransforms: TwoWayMap<String, String> = TwoWayMap()
        val indexTransforms: TwoWayMap<Int, Int> = TwoWayMap()

        val a = 'a'.toInt()
        (0 until 16).forEach {
            val strChar = (a + it).toChar().toString()
            symbolTransforms.put(strChar, strChar)
            indexTransforms.put(it, it)
        }

        input.first()
                .split(",")
                .forEach {
                    val type = it[0]
                    val rest = it.substring(1)
                    when(type) {
                        's' -> addSpinTransform(indexTransforms, rest.toInt())
                        'x' -> swapValues(indexTransforms, rest.split("/").map(String::toInt))
                        'p' -> swapValues(symbolTransforms, rest.split("/"))
                    }

                }

        if(!partTwo) {
            return computeFinalString(a, indexTransforms, symbolTransforms)
        }

        val (newIndex, newSymbols) = do1000Iterations(indexTransforms, symbolTransforms)
        val (secondIndex, secondSymbols) = do1000Iterations(newIndex, newSymbols)
        val (finalIndex, finalSymbols) = do1000Iterations(secondIndex, secondSymbols)


        return computeFinalString(a, finalIndex, finalSymbols)


    }

    private fun do1000Iterations(indexTransforms: TwoWayMap<Int, Int>, symbolTransforms: TwoWayMap<String, String>): Pair<TwoWayMap<Int, Int>, TwoWayMap<String, String>> {
        val indexTransformsOverTime = mutableListOf<TwoWayMap<Int, Int>>(indexTransforms)
        val symbolTransformsOverTime = mutableListOf<TwoWayMap<String, String>>(symbolTransforms)

        for (i in 1 until 1000) {
            indexTransformsOverTime.add(indexTransforms.copy())
            symbolTransformsOverTime.add(symbolTransforms.copy())
        }

        val newIndex = combine(indexTransformsOverTime)
        val newSymbols = combine(symbolTransformsOverTime)
        return Pair(newIndex, newSymbols)
    }

    private fun <K> combine(transforms: MutableList<TwoWayMap<K, K>>): TwoWayMap<K, K> {
        val newMap = transforms.removeAt(0)
        transforms.forEach { t ->
            newMap.entries().forEach {
                val newValue= t.getValue(it.value)
                newMap.put(it.key, newValue)
            }
        }
        return newMap
    }

    private fun computeFinalString(a: Int, indexTransforms: TwoWayMap<Int, Int>, symbolTransforms: TwoWayMap<String, String>): String {
        val res: Array<String> = Array(16, { "" })
        (0 until 16).forEach {
            val strChar = (a + it).toChar().toString()
            val newIndex = indexTransforms.getValue(it)
            val newChar = symbolTransforms.getValue(strChar)
            res[newIndex] = newChar
        }
        return res.joinToString("")
    }

    private fun <K, V> swapValues(twoWayMap: TwoWayMap<K, V>, pair: List<V>) {
        val (i1, i2) = pair

        val i1Key = twoWayMap.getKey(i1)
        val i2Key = twoWayMap.getKey(i2)

        val i1Val = twoWayMap.getValue(i1Key)
        val i2Val = twoWayMap.getValue(i2Key)

        twoWayMap.put(i1Key, i2Val)
        twoWayMap.put(i2Key, i1Val)
    }

    private fun addSpinTransform(indexTransforms: TwoWayMap<Int, Int>, spinSize: Int) {
        val newMap = TwoWayMap<Int, Int>()
        indexTransforms.entries()
                .forEach {
                    val newIndex = (it.value + spinSize) % indexTransforms.size()
                    newMap.put(it.key, newIndex)
                }
        indexTransforms.putAll(newMap)
    }
}
