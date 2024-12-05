package day_04

import Vec2
import readExample
import readInput
import kotlin.time.times

enum class Direction {
    UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
}

val DirectionVectors = mapOf<Direction, Vec2>(
    Direction.UP to Vec2(0, -1),
    Direction.DOWN to Vec2(0, 1),
    Direction.LEFT to Vec2(-1, 0),
    Direction.RIGHT to Vec2(1, 0),
    Direction.UP_LEFT to Vec2(-1, -1),
    Direction.UP_RIGHT to Vec2(1, -1),
    Direction.DOWN_LEFT to Vec2(-1, 1),
    Direction.DOWN_RIGHT to Vec2(1, 1)
)

data class WordSearchPuzzle(val puzzle: List<List<Char>>) {
    private tailrec fun solveSingle(pos: Vec2, i: Int, direction: Vec2, word: String): Boolean {
        if (/*(i < 0 && i == -1 * word.length) ||*/ i == word.length - 1) {
//            println("correct")
            return true
        }

        val nextPos = pos + direction;

        if (nextPos.x < 0 || nextPos.y < 0 || nextPos.y >= puzzle.size || nextPos.x >= puzzle.first().size) {
//            println("out of bounds")
            return false
        }

        val nextChar = /*if (i < 0) word[word.length - 1 + i] else*/ word[i + 1]
        if (nextChar != puzzle[nextPos.y][nextPos.x]) {
//            println("next incorrect")
            return false
        }
        return solveSingle(nextPos, /*if (i < 0) i - 1 else*/ i + 1, direction, word)
    }

    fun solve(word: String = "XMAS", directions: Collection<Vec2> = DirectionVectors.values, searchIndex: Int = 0): List<Vec2> {
        val results = mutableListOf<Vec2>()

        for (y in puzzle.indices) {
            inner@ for (x in puzzle.first().indices) {
                val current = puzzle[y][x]
                val pos = Vec2(x, y)

//                if (!(current == word.first() || current == word.last())) continue@inner
                if (current != word.first()) continue@inner
//                println("x: $x, y: $y")

//                val startI = if (current == word.first()) 0 else -1

                results.addAll(directions.mapNotNull { direction ->
//                    println("direction: ${direction.key.name}")
                    if (solveSingle(pos, 0, direction, word)) pos + direction * searchIndex else null
                })
            }
        }

        return results
    }

    fun solveX(): Int {
        val directions = listOf(
            DirectionVectors[Direction.DOWN_LEFT]!!,
            DirectionVectors[Direction.DOWN_RIGHT]!!,
            DirectionVectors[Direction.UP_LEFT]!!,
            DirectionVectors[Direction.UP_RIGHT]!!
        )
        val all = solve("MAS", directions,1)

        return all.size - all.toSet().size
    }
}


fun main() {
//    val input = readExample(4).filter{it.isNotEmpty()}
    val input = readInput(4).filter { it.isNotEmpty() }

    val puzzle = WordSearchPuzzle(input.map { it.asSequence().toList() })
    val result1 = puzzle.solve()
    val result2 = puzzle.solveX()

    println("result XMAS: ${result1.size}")
    println("result X-MAS: $result2")
}