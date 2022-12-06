import kotlin.system.measureNanoTime

//1109
//3965
fun main() {

    fun part1(input: List<String>): Int {
        val inputStr = input[0]
        var start = 0
        var end = 3
        var set = inputStr.substring(start..end).toSet()
        while(set.size != 4) {
            if (end > inputStr.length-1)
                error("Invalid Input")
            start++
            end++
            set = inputStr.substring(start..end).toSet()
        }

        return end+1
    }

    fun part2(input: List<String>): Int {

        val inputStr = input[0]
        var start = 0
        var end = 13
        var set = inputStr.substring(start..end).toSet()
        while(set.size != 14) {
            if (end > inputStr.length-1)
                error("Invalid Input for part2")
            start++
            end++
            set = inputStr.substring(start..end).toSet()
        }

        return end+1
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
