fun main() {
    fun part1(input: List<String>): Int {
        val totalCalories = mutableListOf<Int>()
        var currentTotal = 0
        input.forEach {
            if (it.isBlank()) {
                totalCalories.add(currentTotal)
                currentTotal = 0
            } else {
                currentTotal += it.toInt()
            }
        }

        return totalCalories.max()
    }

    fun part2(input: List<String>): Int {
        val totalCalories = mutableListOf<Int>()
        var currentTotal = 0
        input.forEach {
            if (it.isBlank()) {
                totalCalories.add(currentTotal)
                currentTotal = 0
            } else {
                currentTotal += it.toInt()
            }
        }

        return totalCalories.sorted().takeLast(3).reduce { acc, i -> acc + i }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
