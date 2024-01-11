import kotlin.math.floor

fun main() {

    /*
     list of monkey -> list<List<string>>
     initialize list of item
     initialize itemtoadd
     */
    fun part1(input: List<String>): Int {

        val storage = mutableListOf<MutableList<Int>>()
        var nThMonkey = 0
        val divisibleBy = mutableListOf<Int>()
        val trueThrowTo = mutableListOf<Int>()
        val falseThrowTo = mutableListOf<Int>()
        val operation = mutableListOf<String>()

        for (line in input) {
            if ("Monkey" in line) {
                nThMonkey = line.substringAfter(" ").trim(':').toInt()
                storage += mutableListOf<Int>()
            } else if ("Starting items" in line) {
                storage[nThMonkey] += line.substringAfter(':').split(',').map { it.trim().toInt() }.toMutableList()
            } else if ("Operation" in line) {
                operation += line.substringAfter('=').trim()
            } else if ("Test" in line) {
                divisibleBy += line.substringAfterLast(' ').toInt()
            } else if ("If true" in line) {
                trueThrowTo += line.substringAfterLast(' ').toInt()
            } else if ("If false" in line) {
                falseThrowTo += line.substringAfterLast(' ').toInt()
            }
        }

        val inspectTotal = MutableList(nThMonkey + 1) { 0 }
        var newItem = 0

        repeat(20) {
            for (i in 0..nThMonkey) {
                if (storage[i].isEmpty()) continue

                for (item in storage[i]) {
                    val (_, operator, varTwo) = operation[i].split(" ")
                    when {
                        "old" !in varTwo -> {
                            when (operator) {
                                "+" -> newItem = item + varTwo.toInt()
                                "*" -> newItem = item * varTwo.toInt()
                            }
                        }

                        else -> {
                            when (operator) {
                                "+" -> newItem = item + item
                                "*" -> newItem = item * item
                            }
                        }
                    }
                    newItem = floor((newItem / 3).toDouble()).toInt()
                    if (newItem % divisibleBy[i] == 0) {
                        storage[trueThrowTo[i]] += newItem
                    } else {
                        storage[falseThrowTo[i]] += newItem
                    }
                    inspectTotal[i] += 1
                }
                storage[i].clear()
            }
        }

        inspectTotal.sortDescending()
        return inspectTotal[0] * inspectTotal[1]
    }

    fun part2(input: List<String>): Long {
        val storage = mutableListOf<MutableList<Int>>()
        var nThMonkey = 0
        val divisibleBy = mutableListOf<Int>()
        val trueThrowTo = mutableListOf<Int>()
        val falseThrowTo = mutableListOf<Int>()
        val operation = mutableListOf<String>()
        var supermodulo: Long = 1
        for (line in input) {
            if ("Monkey" in line) {
                nThMonkey = line.substringAfter(" ").trim(':').toInt()
                storage += mutableListOf<Int>()
            } else if ("Starting items" in line) {
                storage[nThMonkey] += line.substringAfter(':').split(',').map { it.trim().toInt() }.toMutableList()
            } else if ("Operation" in line) {
                operation += line.substringAfter('=').trim()
            } else if ("Test" in line) {
                divisibleBy += line.substringAfterLast(' ').toInt()
                supermodulo = supermodulo.toLong() * divisibleBy[nThMonkey]
            } else if ("If true" in line) {
                trueThrowTo += line.substringAfterLast(' ').toInt()
            } else if ("If false" in line) {
                falseThrowTo += line.substringAfterLast(' ').toInt()
            }
        }

        val inspectTotal = MutableList(nThMonkey + 1) { 0 }
        var newItem = 0
//        val commonMod = divisibleBy.map { it }.lcm().toInt()
        repeat(10000) {
            for (i in 0..nThMonkey) {
                if (storage[i].isEmpty()) continue

                for (item in storage[i]) {
                    val (_, operator, varTwo) = operation[i].split(" ")
                    when {
                        "old" !in varTwo -> {
                            when (operator) {
                                "+" -> newItem = (item + varTwo.toInt())
                                "*" -> newItem = (item * varTwo.toInt())
                            }
                        }
                        else -> {
                            when (operator) {
                                "+" -> newItem = item + item
                                "*" -> newItem = item * item
                            }
                        }
                    }

                    val newWorryLevel = newItem.toLong() % supermodulo

                    if ((newWorryLevel % divisibleBy[i]).toInt() == 0) {
                        storage[trueThrowTo[i]] += newWorryLevel.toInt()
                    } else {
                        storage[falseThrowTo[i]] += newWorryLevel.toInt()
                    }
                    inspectTotal[i] += 1
                }
                storage[i].clear()
            }
        }
        println(inspectTotal)
        inspectTotal.sortDescending()
//        val monkeyBusiness = (inspectTotal[0].toLong() * inspectTotal[1].toLong())
//        println(monkeyBusiness)
        return (inspectTotal[0].toLong() * inspectTotal[1].toLong())
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input_test")
    check(part1(testInput) == 10605)
    check(part2(testInput) == 2713310158)

    val input = readInput("input")
//    part1(input).println()
    part2(input).println()
}
