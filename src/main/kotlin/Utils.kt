import java.io.File

fun readInput(day: Int): List<String> {
    val input = File("src/main/resources/day_${"%02d".format(day)}/input").readLines()
    return input;
}

fun readExample(day: Int): List<String> {
    val input = File("src/main/resources/day_${"%02d".format(day)}/example").readLines()
    return input;
}