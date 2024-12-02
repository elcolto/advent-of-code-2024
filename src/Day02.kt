import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int = input.reports().count { levels ->
        levels.isAscending() or levels.isDescending()
    }

    fun part2(input: List<String>): Int = input.reports().count { levels ->
        val dampenedReports = levels.indices.map { levels.filterIndexed { index, _ -> index != it } }
        (levels.isAscending() or levels.isDescending()) || dampenedReports.count { it.isAscending() or it.isDescending() } > 0
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

fun List<String>.reports(): List<List<Int>> = map { line ->
    line.split(" ")
        .map { it.toInt() }
}

fun List<Int>.iterating(block: (Int, Int) -> Boolean) = zipWithNext { first, second ->
    block(first, second) && (abs(second - first) in 1..3)
}.all { it }


fun List<Int>.isAscending() = iterating { first, second -> first < second }

fun List<Int>.isDescending() = iterating { first, second -> first > second }
