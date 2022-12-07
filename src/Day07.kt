import kotlin.system.measureNanoTime

fun main() {

    fun solve(input: List<String>, isPart1: Boolean): Long {
        val currentDirectoryTree = mutableListOf<String>()
        val directorySizes = hashMapOf<List<String>, Long>()

        input.forEach {
            val commandSplit = it.split(" ")
            val isCommand = commandSplit.first() == "$"
            if (isCommand) {
                // this is command
                when(commandSplit[1]) {
                    "cd" -> {
                        when(val directory = commandSplit[2]) {
                            "/" -> {
                                currentDirectoryTree.clear()
                                currentDirectoryTree.add("/")
                            }
                            ".." -> currentDirectoryTree.removeLast()
                            else -> currentDirectoryTree.add(directory)
                        }
                    }
                    "ls" -> Unit
                }
            } else {
                // this is not command
                commandSplit[0].toIntOrNull()?.let { fileSize ->
                    currentDirectoryTree.reversed().windowed(currentDirectoryTree.size, partialWindows = true).forEach { directory ->
                        directorySizes[directory] = (directorySizes[directory] ?: 0) + fileSize
                    }
                }
            }
        }

        return if (isPart1) {
            directorySizes.filter { it.value <=  100000 }.values.sum()
        } else {
            val unUsedSpace = 70000000 - directorySizes[listOf("/")]!!
            val requiredSpace = 30000000 - unUsedSpace
            directorySizes.filter { it.value >= requiredSpace }.values.min()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(solve(testInput, true) == 95437L)
    check(solve(testInput, false) == 24933642L)

    val input = readInput("Day07")
    val timeTaken = measureNanoTime {
        println(solve(input, true))
        println(solve(input, false))
    }
    println("Time taken: $timeTaken")

}
