fun main() {
    fun part1(input: List<String>): Int {
        val maxTop = input.first().toMutableList()
        val edgeBottom = input.last().toMutableList()
        var visibleTrees = maxTop.size + edgeBottom.size

        for (i in 1..input.lastIndex-1) {
            val trees = input[i]
            var maxLeft = trees.first()
            visibleTrees += 2


            for (index in 1..trees.lastIndex-1) {
                val isVisible = MutableList(4) {false}
                val singleTree = trees[index]

                if (singleTree.digitToInt() > maxLeft.digitToInt()) {
                    maxLeft = singleTree
                    isVisible[0] = true
                }

                if (singleTree.digitToInt() > maxTop[index].digitToInt()) {
                    maxTop[index] = singleTree
                    isVisible[1] = true
                }


                if (singleTree.digitToInt() > trees.substring(index+1).map { it.digitToInt() }.max()) {
                    isVisible[2] = true
                }

                val checkColumn = mutableListOf<Int>()
                for (column in i+1..input.lastIndex) {
                    checkColumn.add(input[column][index].digitToInt())
                }

                if (singleTree.digitToInt() > checkColumn.max()) {
                    isVisible[3] = true
                }


                if (isVisible.any { it }) {
                    visibleTrees += 1
                }

            }
        }

        return visibleTrees
    }

    fun part2(input: List<String>): Int {
        val scenicScore = mutableListOf<Int>()

    for (i in 1..input.lastIndex-1) {
        val trees = input[i]

        for (index in 1..trees.lastIndex-1) {
            val currTree = trees[index].digitToInt()
            val checkLeft = trees.substring(0, index).map { it.digitToInt() }
            var leftScore = 0
            for (leftIndex in checkLeft.lastIndex downTo 0) {
                leftScore += 1
                if (currTree <= checkLeft[leftIndex]) break
            }

            val checkUp = mutableListOf<Int>()
            for (column in i-1 downTo 0) {
                checkUp.add(input[column][index].digitToInt())
            }
            var upScore = 0
            for (up in checkUp) {
                upScore += 1
                if (currTree <= up) break
            }

            var rightScore = 0
            val checkRight = trees.substring(index+1).map { it.digitToInt() }
            for (right in checkRight) {
                rightScore += 1
                if (currTree <= right) break
            }


            val checkDown = mutableListOf<Int>()
            for (column in i+1..input.lastIndex) {
                checkDown.add(input[column][index].digitToInt())
            }
            var downScore = 0

            for (down in checkDown) {
                downScore += 1
                if (currTree <= down) break
            }
            scenicScore.add(leftScore*upScore*rightScore*downScore)
        }
    }
        return scenicScore.max()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}
