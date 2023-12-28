fun main() {
    fun part1(input: List<String>): Int {

        var myScore = 0
        val scoreMap = mapOf("A" to 1, "B" to 2, "C" to 3, "X" to 1, "Y" to 2, "Z" to 3)

        val data = input.map {
            var (opps, me) = it.split(" ")

            if (scoreMap[opps]!! - scoreMap[me]!! == -2 || scoreMap[opps]!! - scoreMap[me]!! == 1) {
                myScore += scoreMap[me]!!
            } else if (scoreMap[opps]!! - scoreMap[me]!! == 2 || scoreMap[opps]!! - scoreMap[me]!! == -1){
                myScore += 6 + scoreMap[me]!!
            } else if (scoreMap[opps]!! == scoreMap[me]!!){
                myScore += 3 + scoreMap[me]!!
            }
        }
        return myScore
    }

    fun part2(input: List<String>): Int {
        val scoreMap = mapOf("A" to 1, "B" to 2, "C" to 3, "X" to 0, "Y" to 3, "Z" to 6)
        var myScore = 0

        input.map {
            var (opps, me) = it.split(" ")
            if (scoreMap[me]!! == 0 && scoreMap[opps]!! == 1) {
                myScore += 3
            } else if (scoreMap[me]!! == 0) {
                myScore += scoreMap[opps]!! - 1
            } else if (scoreMap[me]!! == 6) {
                myScore += (scoreMap[opps]!! % 3) + 1 + scoreMap[me]!!
            } else if (scoreMap[me]!! == 3) {
                myScore += scoreMap[opps]!! + scoreMap[me]!!
            }
        }
        return myScore
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)
    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}
