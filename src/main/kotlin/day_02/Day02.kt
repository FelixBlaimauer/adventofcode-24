package day_01

import readExample
import readInput
import kotlin.math.abs

const val MIN_DIFF = 1;
const val MAX_DIFF = 3;

val DIFF_RANGE = MIN_DIFF..MAX_DIFF;

fun checkSafe(asc: Boolean, left: Int, right: Int): Boolean {
    val diff = right-left
    val absDiff = abs(diff)

    if ((asc && diff < 0) || (!asc && diff > 0)) return false;
    if (absDiff !in DIFF_RANGE) return false;
    return true;
}

fun isSafeReport(report: List<Int>): Boolean {
    val asc = report[0] < report[1];
//    var unsafeCount = false
//    var skipNext = false

//    println(report)
//    println("asc: $asc")

    val zipped = report.zipWithNext()

    zipped.forEachIndexed { i, (left, right) ->
//        println("left: $left, right: $right")
//        if (skipNext) {
//            println("skip")
//            skipNext = false;
//        } else {
        var safe = checkSafe(asc, left, right)
//        if (!safe) println("prob: $left   $right")
//        else println("good: $left   $right")
        // println("Tuple $left - $right is $safe")
//            if (!safe && !unsafeCount && i + 1 < zipped.size) {
//                safe = checkSafe(asc, left, zipped[i + 1].second)
//                skipNext = true
//                unsafeCount = true
//            }
//            if (!safe) {
//                println("unafe")
//                return false
//            };
        if (!safe) return false;
    }

    return true
}

fun main() {
     val input = readInput(2).filter { it.isNotEmpty() }
    // val input = readExample(2).filter { it.isNotEmpty() }

    val reports = input.map { it.split(" ").map { n -> n.toInt() } }

    val safeReports = reports.count { report ->
//        println(report)
        if (isSafeReport(report)) return@count true
//        println("was unsafe")
        report.forEachIndexed { i, _ ->
            val r = report.toMutableList()
            r.removeAt(i)
            if (isSafeReport(r)) return@count true
        }

        return@count false;
    }

    println("Number of safe reports: $safeReports")
}