package utils

import java.io.File

fun readFile(path: String) : List<String> = File(path).readLines()

