package utils

fun splitAtWhitespace(s: String): List<String> = s.split(Regex("\\s+")).map(String::trim)
