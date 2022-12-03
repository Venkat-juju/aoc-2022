fun main() {

    fun Char.toPriority() = this - (if (isUpperCase()) 'A' - 27 else 'a' - 1)

    fun part1(input: List<String>): Int {
        return input.sumOf { ruckSack ->
            val compartments = ruckSack.chunked(ruckSack.length / 2)
            val letter = ((compartments.first().toSet()) intersect compartments[1].toSet()).first()
            letter.toPriority()
        }
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3).sumOf { sacks ->
            val commonItem = (sacks.first().toSet() intersect sacks[1].toSet()) intersect sacks[2].toSet()
            commonItem.first().toPriority()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    println(part2(testInput))
    check(part2(testInput) == 70)

    val input = readInput("input")
    println(part1(input))
    println(part2(input))

}
