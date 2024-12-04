package day_01

import readInput
import kotlin.math.abs

fun solveLvl1(coordsLeft: MutableList<Int>, coordsRight: MutableList<Int>): Int {
    coordsLeft.sort()
    coordsRight.sort()

    val zipped = coordsLeft.zip(coordsRight)
    val distances = zipped.map{ abs(it.first - it.second)}
    val sum = distances.sum()

    return sum
}

fun solveLvl2(coordsLeft: MutableList<Int>, coordsRight: MutableList<Int>): Int {
    val score = coordsLeft.sumOf {
        it * coordsRight.count{r -> r == it}
    }

    return score
}

fun main() {
    val input = readInput(1).filter { it.isNotEmpty() }

    val coordsLeft = mutableListOf<Int>();
    val coordsRight = mutableListOf<Int>();

    input.forEach {
        val (left, right) = it.split(Regex("\\s+"))
        coordsLeft.add(left.toInt())
        coordsRight.add(right.toInt())
    }

    val sum = solveLvl1(coordsLeft, coordsRight);
    println("Sum of distances: $sum")

    val similarityScore = solveLvl2(coordsLeft, coordsRight);
    println("Similarity score: $similarityScore")
}