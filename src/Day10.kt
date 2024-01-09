fun main() {

    fun part1(input: List<String>): Int {
        var cycle = 0
        var register = 1
        var repeatCycle = 0
        val signalStrength = mutableListOf<Int>()

        input.forEach { cmd ->
            var value = 0
            when {
                cmd == "noop" -> repeatCycle = 1
                "addx" in cmd -> {
                    repeatCycle = 2
                    val addxVal = cmd.split(" ")
                    value = addxVal[1].toInt()
                }
            }

            repeat(repeatCycle) {
                cycle += 1
                if (cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220) {
                    signalStrength += (cycle*register)
                }
            }
            register += value
        }

        return signalStrength.sum()
    }

    fun part2(input: List<String>): String {
        var cycle = 0
        var register = 1
        var repeatCycle = 0
        var renderImage = ""


        input.forEach { cmd ->
            var value = 0
            val sprite = register..register + 2

            when {
                cmd == "noop" -> repeatCycle = 1
                "addx" in cmd -> {
                    repeatCycle = 2
                    val addxVal = cmd.split(" ")
                    value = addxVal[1].toInt()
                }
            }

            repeat(repeatCycle) {
                cycle += 1
                if (cycle in sprite) renderImage += "#" else renderImage += "."

                if (cycle == 40) {
                    renderImage += "\n"
                    cycle = 0
                }
            }
            register += value
        }
        return renderImage
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input_test")
//    check(part1(testInput) == 13140)
//    check(part2(testInput) == "202")

    val input = readInput("input")
//    part1(input).println()
    part2(input).println()
}
