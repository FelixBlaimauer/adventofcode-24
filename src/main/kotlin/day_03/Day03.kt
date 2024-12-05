package day_03

import readExample
import readInput

fun main() {
//    val input = readExample(3).filter { it.isNotEmpty() }.joinToString("")
     val input = readInput(3).filter { it.isNotEmpty() }.joinToString("")

    val mulRegex = Regex("""(?<=mul\()[0-9]{1,3},[0-9]{1,3}(?=\))""")
    val conditionRegex = Regex("""((don't)|(do))(?=\(\))""")

    val multiplications = mulRegex.findAll(input)
    val conditions = conditionRegex.findAll(input)

    val ranges = conditions.associate {
        (it.range.last) to (it.value.last() == 'o')
    }

    val goodRanges =
        (listOf(0..conditions.first().range.first) +
                ranges.entries.zipWithNext().mapNotNull { (left, right) ->
                    if (left.value) left.key..right.key else null
                }).toMutableList()

    val last = ranges.entries.last()
    if (last.value) {
        goodRanges.add(last.key..input.length)
    }

    val result = multiplications.fold(0) { c, mul ->
        val (a, b) = mul.value.split(",").map { n -> n.toInt() }
        if (goodRanges.any{mul.range.first in it}) c + a * b else c
    }

    println("result: $result")
}