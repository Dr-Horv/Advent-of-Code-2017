
package solutions.day08

import solutions.Solver
import utils.splitAtWhitespace
enum class Operation {
    INC,
    DEC
}

fun String.toOperation(): Operation = when(this) {
    "inc" -> Operation.INC
    "dec" -> Operation.DEC
    else -> throw RuntimeException("Operation unparsable: $this")
}

fun String.toCondition(): Condition = when(this) {
    ">" -> Condition.GT
    "<" -> Condition.LT
    ">=" -> Condition.GTE
    "==" -> Condition.EQ
    "<=" -> Condition.LTE
    "!=" -> Condition.NEQ
    else -> throw RuntimeException("Condition unparsable $this")
}

enum class Condition {
    GT,
    LT,
    GTE,
    EQ,
    LTE,
    NEQ
}

fun Int.checkCondition(condition: Condition, x: Int): Boolean = when(condition) {
    Condition.GT -> this > x
    Condition.LT -> this < x
    Condition.GTE -> this >= x
    Condition.EQ -> this == x
    Condition.LTE -> this <= x
    Condition.NEQ -> this != x
}

fun Int.doOperation(operation: Operation, amount: Int) : Int = when(operation) {
    Operation.INC -> this + amount
    Operation.DEC -> this - amount
}

data class Instruction(val registry: String,
                       val operation: Operation,
                       val amount: Int,
                       val registryForCondition: String,
                       val condition: Condition,
                       val comparision: Int)

class Day8: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {

        val instructions = input.map(String::splitAtWhitespace)
                .map {
                    Instruction(
                            registry = it[0],
                            operation = it[1].toOperation(),
                            amount = it[2].toInt(),
                            registryForCondition = it[4],
                            condition = it[5].toCondition(),
                            comparision = it[6].toInt()
                    )
                }

        val registries = mutableMapOf<String, Int>().withDefault { 0 }

        var totalMax = Int.MIN_VALUE

        instructions.forEach {
            if(registries.getValue(it.registryForCondition)
                    .checkCondition(it.condition, it.comparision))  {
                val value = registries.getValue(it.registry)
                val newValue = value.doOperation(it.operation, it.amount)
                if(newValue > totalMax) {
                    totalMax = newValue
                }
                registries.put(it.registry, newValue)
            }
        }

        val max = registries.values.max()!!

        return if(partTwo) {
            totalMax.toString()
        } else {
            registries.values.max()!!.toString()
        }

    }
}
