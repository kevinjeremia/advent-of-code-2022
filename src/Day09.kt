import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {

    fun part1(input: List<String>): Int {
        val hCoordinate = mutableListOf(0,0)
        val tCoordinate = mutableListOf(0,0)
        val visited = mutableListOf<MutableList<Int>>()

        for (line in input) {
            val (cmd, strStep) = line.split(" ")
            val step = strStep.toInt()

            repeat(step){
                when (cmd) {
                    "U" -> hCoordinate[1] += 1
                    "D" -> hCoordinate[1] -= 1
                    "R" -> hCoordinate[0] += 1
                    "L" -> hCoordinate[0] -= 1
                }

                if (kotlin.math.abs(hCoordinate[0] - tCoordinate[0]) == 2 || kotlin.math.abs(hCoordinate[1] - tCoordinate[1]) == 2) {
                    when (cmd) {
                        "U" -> {
                            tCoordinate[0] = hCoordinate[0]
                            tCoordinate[1] = hCoordinate[1] - 1
                        }

                        "D" -> {
                            tCoordinate[0] = hCoordinate[0]
                            tCoordinate[1] = hCoordinate[1] + 1
                        }

                        "R" -> {
                            tCoordinate[0] = hCoordinate[0] - 1
                            tCoordinate[1] = hCoordinate[1]
                        }

                        "L" -> {
                            tCoordinate[0] = hCoordinate[0] + 1
                            tCoordinate[1] = hCoordinate[1]
                        }
                    }
                }
                if (mutableListOf(tCoordinate[0],tCoordinate[1]) !in visited) {
                    visited.add(mutableListOf(tCoordinate[0],tCoordinate[1]))
                }
            }
        }
//        println(visited)
        return visited.size
    }

    fun part2(input: List<String>): Int {
        val visited = mutableListOf<MutableList<Int>>()
        val knotList = MutableList(10) { mutableListOf(0,0) }

        for (line in input) {
            val (cmd, strStep) = line.split(" ")
            val step = strStep.toInt()


            repeat(step){
                for (i in 0..8) {
                    val head = knotList[i]
                    val tail = knotList[i+1]
                    when {
                        i == 0 && cmd == "U" -> head[1] += 1
                        i == 0 && cmd == "D" -> head[1] -= 1
                        i == 0 && cmd == "R" -> head[0] += 1
                        i == 0 && cmd == "L" -> head[0] -= 1
                    }

                    val deltaX = head[0] - tail[0]
                    val deltaY = head[1] - tail[1]
                    if (deltaX.absoluteValue <= 1 && deltaY.absoluteValue <= 1) continue

                    tail[0] = if (deltaX.absoluteValue >= deltaY.absoluteValue) head[0] - deltaX.sign else head[0]
                    tail[1] = if (deltaX.absoluteValue <= deltaY.absoluteValue) head[1] - deltaY.sign else head[1]

                }


                if (mutableListOf(knotList[9][0],knotList[9][1]) !in visited) {
                    visited.add(mutableListOf(knotList[9][0],knotList[9][1]))
                }
            }
        }

        return visited.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input_test")
//    check(part1(testInput) == 13)
    check(part2(testInput) == 36)

    val input = readInput("input")
//    part1(input).println()
    part2(input).println()
}