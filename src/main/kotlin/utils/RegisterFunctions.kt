package utils

fun mod(registers: MutableMap<String, Long>, s: String, toInt: String) {
    registerOperation(registers, s, toInt, Long::mod)
}

fun mul(registers: MutableMap<String, Long>, s: String, toInt: String) {
    registerOperation(registers, s, toInt, Long::times)
}

fun add(registers: MutableMap<String, Long>, s: String, toInt: String) {
    registerOperation(registers, s, toInt, Long::plus)
}

fun sub(registers: MutableMap<String, Long>, s: String, toInt: String) {
    registerOperation(registers, s, toInt, Long::minus)
}

fun registerOperation(registers: MutableMap<String, Long>, s: String, toInt: String, operation: (Long, Long) -> Long) {
    registers.put(s, operation(registers.getValue(s), getValue(registers, toInt)))
}

fun set(registers: MutableMap<String, Long>, s: String, toInt: String) {
    registers.put(s, getValue(registers, toInt))
}

fun getValue(registers: MutableMap<String, Long>, s: String): Long {
    return try {
        s.toLong()
    } catch (e: NumberFormatException) {
        registers.getValue(s)
    }
}