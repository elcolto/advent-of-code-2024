fun main() {

    fun part1(input: List<String>): Int = input.sumOf { line ->
        MULTIPLY_REGEX.toRegex().findAll(line).sumOf { matchResult ->
            multiplyResult(matchResult)
        }
    }

    fun part2(input: List<String>): Int {
        val line = input.joinToString(separator = "")
        return "do\\(\\)|don't\\(\\)|${MULTIPLY_REGEX}".toRegex().findAll(line)
            .fold(true to 0) { (enabled, value), result ->
                when (result.value) {
                    "do()" -> true to value
                    "don't()" -> false to value
                    else -> enabled to if (enabled) {
                        value + multiplyResult(result)
                    } else value
                }
            }.second
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}

private fun multiplyResult(matchResult: MatchResult): Int {
    val first = matchResult.groups["first"]?.value?.toInt() ?: 0
    val second = matchResult.groups["second"]?.value?.toInt() ?: 0
    return first * second
}

private const val MULTIPLY_REGEX = "mul\\((?<first>\\d{1,3}),(?<second>\\d{1,3})\\)"
