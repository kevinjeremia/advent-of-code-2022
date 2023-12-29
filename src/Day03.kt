fun main() {
    fun part1(input: List<String>): Int {
        var sumPriorities = 0
        input.forEach { rucksack ->
            val secondComp = rucksack.substring(rucksack.length/2)
            rucksack.forEachIndexed { index: Int, item: Char ->
                if (index < rucksack.length/2 && secondComp.contains(item) && item.isLowerCase()) {
                    sumPriorities += item.code - 96
                    return@forEach
                } else if (index < rucksack.length/2 && secondComp.contains(item)) {
                    sumPriorities += item.code - 38
                    return@forEach
                }
            }
        }
        return sumPriorities
    }

    fun part2(input: List<String>): Int {
        var sumPriorities = 0

        input.chunked(3)
            .forEach { group ->
                group[0].forEachIndexed { index, item: Char ->
                    if (group[1].contains(item) && group[2].contains(item) && item.isLowerCase()) {
                        sumPriorities += item.code - 96
                        return@forEach
                    } else if (group[1].contains(item) && group[2].contains(item)) {
                        sumPriorities += item.code - 38
                        return@forEach
                    }
                }
            }

        return sumPriorities
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}
