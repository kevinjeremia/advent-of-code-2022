fun main() {
    val pattern = """[$] cd (.*)|(\d+).*""".toRegex()
    fun part1(input: List<String>): Int {
        val dirMap = buildMap {
            put("",0)
            var cwd = ""

            for (cmd in input) {
                val match = pattern.matchEntire(cmd) ?: continue
                match.groups[1]?.value?.let { dir ->
                    cwd = when (dir) {
                        "/" -> ""
                        ".." -> cwd.substringBeforeLast("/","")
                        else -> if (cwd.isEmpty()) dir else "$cwd/$dir"
                    }
                } ?: match.groups[2]?.value?.toIntOrNull()?.let { size ->
                    var dir = cwd
                    while (true) {
                        put(dir, getOrElse(dir){0} + size)
                        if (dir.isEmpty()) break
                        dir = dir.substringBeforeLast("/", "")
                    }
                }
            }
        }
        return dirMap.values.sumOf { if (it <= 100000) it else 0}
    }

    fun part2(input: List<String>): Int {
        val dirMap = buildMap {
            put("",0)
            var cwd = ""

            for (cmd in input) {
                val match = pattern.matchEntire(cmd) ?: continue
                match.groups[1]?.value?.let { dir ->
                    cwd = when (dir) {
                        "/" -> ""
                        ".." -> cwd.substringBeforeLast("/","")
                        else -> if (cwd.isEmpty()) dir else "$cwd/$dir"
                    }
                } ?: match.groups[2]?.value?.toIntOrNull()?.let { size ->
                    var dir = cwd
                    while (true) {
                        put(dir, getOrElse(dir){0} + size)
                        if (dir.isEmpty()) break
                        dir = dir.substringBeforeLast("/", "")
                    }
                }
            }
        }
        val unusedSpace = 70000000 - dirMap[""]!!
        if (unusedSpace < 30000000) {
            val needToBeDeleted = 30000000 - unusedSpace
            return dirMap.values.filter { it > needToBeDeleted }.min()
        }
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}
