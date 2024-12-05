import java.io.File

data class Vec2(val x: Int, val y: Int) {
    companion object {
        fun add(a: Vec2, b: Vec2): Vec2 {
            return Vec2(a.x + b.x, a.y + b.y)
        }

        fun subtract(a: Vec2, b: Vec2): Vec2 {
            return Vec2(a.x - b.x, a.y - b.y)
        }

        fun multiply(a: Vec2, b: Int): Vec2 {
            return Vec2(a.x * b, a.y * b)
        }
    }

    fun add(vec: Vec2): Vec2 {
        return Companion.add(this, vec)
    }

    fun subtract(vec: Vec2): Vec2 {
        return Companion.subtract(this, vec)
    }

    fun multiply(mul: Int): Vec2 {
        return Companion.multiply(this, mul)
    }

    operator fun plus(vec: Vec2): Vec2 {
        return this.add(vec)
    }

    operator fun minus(vec: Vec2): Vec2 {
        return this.subtract(vec)
    }

    operator fun times(mul: Int): Vec2 {
        return this.multiply(mul)
    }
}

fun readInput(day: Int): List<String> {
    val input = File("src/main/resources/day_${"%02d".format(day)}/input").readLines()
    return input;
}

fun readExample(day: Int): List<String> {
    val input = File("src/main/resources/day_${"%02d".format(day)}/example").readLines()
    return input;
}