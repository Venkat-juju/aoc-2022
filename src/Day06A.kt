import kotlin.system.measureNanoTime

//1109
//3965
fun main() {

    fun solve(input: List<String>, offset: Int): Int {
        val charactersMap = hashMapOf<Char, Int>()
        var inputStr = input[0]
        var start = 0
        inputStr.forEachIndexed { currentIndex, currentChar ->
            val previouslyPresentIndex = charactersMap.get(currentChar)
            if (previouslyPresentIndex == null) {
                if (currentIndex - start == offset - 1) {
                    return currentIndex + 1
                }
            } else {
                if (previouslyPresentIndex < start) {
                    if (currentIndex - start == offset - 1) {
                        return currentIndex + 1
                    }
                } else {
                    start = previouslyPresentIndex + 1
                }
            }
            charactersMap[currentChar] = currentIndex
        }

        error("Invalid input")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(solve(testInput, 4) == 11)
    check(solve(testInput, 14) == 26)

    val input = readInput("Day06")
    val timeTaken = measureNanoTime {
        println(solve(input, 4))
        println(solve(input, 14))
    }
    println("Time taken: $timeTaken ns")
}
