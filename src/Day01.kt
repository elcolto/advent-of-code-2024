import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        val numbers = readNumbers(input)

        val firsts = numbers.map { it.first }.sorted()
        val seconds = numbers.map { it.second }.sorted()

        val distances = firsts.mapIndexed { index, first ->
            abs(first - seconds[index])
        }.sum()

        return distances
    }

    fun part2(input: List<String>): Int {
        val numbers = readNumbers(input)
        val firsts = numbers.map { it.first }
        val seconds = numbers.map { it.second }

        return firsts.sumOf { number ->
            number * seconds.count { it == number }
        }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

fun readNumbers(input: List<String>): List<Pair<Int, Int>> = input.map { line ->
    line.split(" ")
        .mapNotNull { it.toIntOrNull() }
        .let { it.first() to it.last() }
}

