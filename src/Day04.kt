fun main() {

    fun part1(input: List<String>): Int {
        var totalSubset = 0
        input.forEach { section ->
            var splitting = section.split(",")
                .map {
                    var (from,until) = it.split("-")
                    (from.toInt()..until.toInt()).toList()
                }

            if (splitting[0].first() >= splitting[1].first() && splitting[0].last() <= splitting[1].last()) {
                totalSubset += 1
            } else if (splitting[0].first() <= splitting[1].first() && splitting[0].last() >= splitting[1].last()) {
                totalSubset += 1
            }
        }
        return totalSubset
    }

    fun part2(input: List<String>): Int {
        var totalOverlap = 0

        input.forEach { section ->
            var splitting = section.split(",")
                .map {
                    var (from,until) = it.split("-")
                    (from.toInt()..until.toInt()).toList()
                }

            if (splitting[0].first() >= splitting[1].first() && splitting[0].last() <= splitting[1].last()) {
                totalOverlap += 1
            } else if (splitting[0].first() <= splitting[1].first() && splitting[0].last() >= splitting[1].last()) {
                totalOverlap += 1
            } else if (splitting[0].last() >= splitting[1].first() && splitting[0].first() <= splitting[1].first()) {
                totalOverlap += 1
            } else if (splitting[1].last() >= splitting[0].first() && splitting[1].first() <= splitting[0].first()) {
                totalOverlap += 1
            }
        }

        return totalOverlap
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}
