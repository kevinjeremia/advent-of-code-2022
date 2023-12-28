fun main() {
    fun part1(input: List<String>): Int {
        val data = input.joinToString(separator = "\n")
            .split("\n\n")
            .map { calories ->
                calories.lines()
                    .map { it.toInt() }
            }

        return data.maxOf { it.sum() }
    }

    fun part2(input: List<String>): Int {
        return input.joinToString(separator = "\n")
            .split("\n\n")
            .map { calories ->
                calories.lines()
                    .sumOf { it.toInt() }
            }.sortedDescending()
            .take(3)
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    // val testInput = readInput("Day01_test")
    // check(part1(testInput) == 1)

    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}
