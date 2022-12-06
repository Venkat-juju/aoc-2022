import kotlin.system.measureNanoTime

fun main() {

    fun part1(input: List<String>): Int {
        val inputStr = input[0]
        var start = 0
        var end = 3
        var isMarkerFound = false
        while(!isMarkerFound) {
            val set = inputStr.substring(start..end).toSet()
            if (set.size == 4) {
                isMarkerFound = true
                return end + 1
            } else {
                start++
                end++
            }
        }

        error("Invalid input")
    }

    fun part2(input: List<String>): Int {

        val inputStr = input[0]
        var start = 0
        var end = 13
        var isMarkerFound = false
        while(!isMarkerFound) {
            val set = inputStr.substring(start..end).toSet()
            if (set.size == 14) {
                isMarkerFound = true
                return end + 1
            } else {
                start++
                end++
            }
        }

        error("Invalid input")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 26)

    val input = readInput("Day06")
    val timeTaken = measureNanoTime {
        println(part1(input))
        println(part2(input))
    }
    println("Time taken: $timeTaken ns")
}
