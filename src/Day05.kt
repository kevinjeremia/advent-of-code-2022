fun main() {
    fun part1(input: List<String>): String {

        val totalStack = ((input[0].length - 3) / 4) + 1

        val stack = MutableList(totalStack) {mutableListOf<String>()}

        input.forEach { crates ->
            if (crates.isBlank()) {
                return@forEach
            } else if (crates[1].isDigit()) {
                for (s in stack) s.reverse()
                return@forEach
            }

            var pointerStack = 0
            if (!crates[1].isLowerCase()) {
                for (i in 1..crates.length - 1 step 4) {
                    if (!crates[i].isWhitespace()) {
                        stack[pointerStack].add(crates[i].toString())
                    }
                    pointerStack += 1
                }
            } else if (crates[1].isLowerCase()) {
                val command = crates.split(" ")

                val toBeMoved = command[1].toInt()
                val from = command[3].toInt()
                val to = command[5].toInt()

                repeat(toBeMoved) {
                    stack[to-1] += stack[from-1].last()
                    stack[from-1].removeLast()
                }
            }
        }
        var topCrates = ""
        for (crate in stack) topCrates += crate.last()

        return topCrates

    }

    fun part2(input: List<String>): String {
        val totalStack = ((input[0].length - 3) / 4) + 1

        val stack = MutableList(totalStack) {mutableListOf<String>()}

        input.forEach { crates ->
            if (crates.isBlank()) {
                return@forEach
            } else if (crates[1].isDigit()) {
                for (s in stack) s.reverse()
                return@forEach
            }

            var pointerStack = 0
            if (!crates[1].isLowerCase()) {
                for (i in 1..crates.length - 1 step 4) {
                    if (!crates[i].isWhitespace()) {
                        stack[pointerStack].add(crates[i].toString())
                    }
                    pointerStack += 1
                }
            } else if (crates[1].isLowerCase()) {
                val command = crates.split(" ")

                val toBeMoved = command[1].toInt()
                val from = command[3].toInt()
                val to = command[5].toInt()

                if (toBeMoved == 1) {
                    stack[to-1] += stack[from-1].last()
                    stack[from-1].removeLast()
                } else if (toBeMoved > 1) {
                    stack[to-1] += stack[from-1]
                        .subList(fromIndex = stack[from-1].lastIndex - toBeMoved + 1,
                            toIndex = stack[from-1].lastIndex + 1)
                    repeat(toBeMoved) {
                        stack[from-1].removeLast()
                    }
                }
            }
        }
        var topCrates = ""
        for (crate in stack) topCrates += crate.last()

        return topCrates
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}
