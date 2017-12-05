package solutions

import junit.framework.Assert.assertEquals
import org.junit.Test

abstract class AbstractDayTest(private val day: Solver) {

    abstract fun getPart1Data() : Map<List<String>, String>
    abstract fun getPart2Data() : Map<List<String>, String>

    @Test
    fun testPart1Data() {
        testData(getPart1Data(), {day.solve(it, false)})
    }

    @Test
    fun testPart2Data() {
        testData(getPart2Data(), {day.solve(it, true)})
    }

    private fun testData(data: Map<List<String>, String>, solve: (input: List<String>) -> String) {
        for(entry in data.entries) {
            assertEquals(entry.value, solve(entry.key))
        }
    }
}