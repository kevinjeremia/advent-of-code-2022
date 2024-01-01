fun main() {
    fun part1(input: List<String>): Int {
        val inputString = input[0]
        var processedChar = 0

        for ((index, letter) in inputString.withIndex()) {
            val end = if (index + 4 <= inputString.length) {
                index + 4
            } else {
                inputString.length
            }

            val marker = inputString.substring(startIndex = index + 1, endIndex = end)

            // first validation
            if (marker.contains(letter)) {
                continue
            } else if (!marker.substring(1).contains(marker[0]) && marker[2] != marker[1]) {
                // second validation
                processedChar = index + 4
                break
            }
        }
        return processedChar
    }

    fun part2(input: List<String>): Int {
        val inputString = input[0]
        var processedChar = 0

        for ((index, letter) in inputString.withIndex()) {
            val end = if (index + 14 <= inputString.length) {
                index + 14
            } else {
                inputString.length
            }

            val marker = inputString.substring(startIndex = index + 1, endIndex = end)

            // first validation
            if (marker.contains(letter)) {
                continue
            }
            // second validation
            var doesNotContain = true
            for ((markerIndex, char) in marker.withIndex()) {
                val newMarker = marker.substring(markerIndex+1)

                if (newMarker.contains(char)) {
                    doesNotContain = false
                    break
                }
            }
            if (doesNotContain) {
                processedChar = index + 14
                break
            }
        }
        return processedChar
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 23)

    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}
