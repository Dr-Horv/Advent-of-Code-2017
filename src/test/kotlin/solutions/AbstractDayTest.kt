package solutions

import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.assertj.core.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class AbstractDayTest(private val day: Solver) {
    data class TestData(val input: List<String>, val output: String)

    abstract fun getPart1Data() : List<TestData>
    abstract fun getPart2Data() : List<TestData>

    @ParameterizedTest
    @MethodSource("getPart1Data")
    fun testPart1Data(data: TestData) {
        assertThat(day.solve(data.input, partTwo = false)).isEqualTo(data.output)
    }

    @ParameterizedTest
    @MethodSource("getPart2Data")
    fun testPart2Data(data: TestData) {
        assertThat(day.solve(data.input, partTwo = true)).isEqualTo(data.output)
    }
}