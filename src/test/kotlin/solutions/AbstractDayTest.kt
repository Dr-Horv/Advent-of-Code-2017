package solutions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class AbstractDayTest(private val day: Solver) {
    data class TestData(val input: List<String>, val output: String)

    abstract fun getPart1Data() : List<TestData>
    abstract fun getPart2Data() : List<TestData>

    @ParameterizedTest
    @MethodSource("getPart1Data")
    fun testPart1Data(data: TestData) {
        assertEquals(data.output, day.solve(data.input, partTwo = false))
    }

    @ParameterizedTest
    @MethodSource("getPart2Data")
    fun testPart2Data(data: TestData) {
        assertEquals(data.output, day.solve(data.input, partTwo = true))
    }
}