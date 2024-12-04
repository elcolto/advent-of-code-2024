fun main() {
    fun List<List<Char>>.getOrNull(position: Position) = this.getOrNull(position.x)?.getOrNull(position.y)

    fun part1(input: List<String>): Int {
        val cluster = input.map { it.toList() }
        return cluster.indices.flatMap { x ->
            cluster[x].indices.map { y ->
                neighbors(Position(x, y)).sumOf {
                    val words = it.mapNotNull { word -> cluster.getOrNull(word) }
                    if (words.joinToString("") == "XMAS") 1 as Int else 0
                }
            }

        }.sum()

    }

    fun part2(input: List<String>): Int {
        val mas = "MAS"
        return (0..input.lastIndex - mas.lastIndex).flatMap { x ->
            (0..input[0].lastIndex - "MAS".lastIndex).map { y ->
                listOf(
                    listOf(input[x][y], input[x + 1][y + 1], input[x + 2][y + 2]).joinToString(""),
                    listOf(input[x][y + 2], input[x + 1][y + 1], input[x + 2][y]).joinToString("")
                )
            }
        }.count { (it[0] == "MAS" || it[0] == "SAM") && (it[1] == "MAS" || it[1] == "SAM") }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}

fun neighbors(of: Position): List<List<Position>> {
    val range = "XMAS".indices
    return listOf(
        range.map { Position(of.x, of.y + it) }, // down
        range.map { Position(of.x + it, of.y) }, // right
        range.map { Position(of.x - it, of.y) }, // left
        range.map { Position(of.x, of.y - it) }, // up
        range.map { Position(of.x + it, of.y + it) }, // down right
        range.map { Position(of.x + it, of.y - it) }, // up right
        range.map { Position(of.x - it, of.y + it) }, // down left
        range.map { Position(of.x - it, of.y - it) }, // up left
    )
}

data class Position(val x: Int, val y: Int)