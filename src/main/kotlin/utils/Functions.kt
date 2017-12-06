package utils

fun String.splitAtWhitespace(): List<String> = this.split(Regex("\\s+")).map(String::trim)
