import kotlin.math.max

fun main() {

    fun isVisible(treeIndex: Int, treeLine: List<Int>): Boolean {
        val treeHeight = treeLine[treeIndex]
        return treeLine.slice(0 until treeIndex).all{it < treeHeight} || treeLine.slice(treeIndex + 1 .. treeLine.lastIndex).all{it < treeHeight}
    }

    fun lineScenicScore(treeIndex: Int, treeLine: List<Int>): Int {
        val leftTrees = treeLine.slice(0 until treeIndex)
        val rightTrees = treeLine.slice(treeIndex + 1 .. treeLine.lastIndex)
        val treeHeight = treeLine[treeIndex]

        var leftVisibleTrees = leftTrees.reversed().indexOfFirst { it >= treeHeight } + 1
        var rightVisibleTrees = rightTrees.indexOfFirst { it >= treeHeight } + 1

        if (leftVisibleTrees == 0) leftVisibleTrees = leftTrees.size
        if (rightVisibleTrees == 0) rightVisibleTrees = rightTrees.size

        return leftVisibleTrees * rightVisibleTrees
    }

    fun part1(input: List<String>): Int {
        val treeGrid = mutableListOf<List<Int>>()
        input.forEach {
            treeGrid.add(it.split("").filterNot(String::isBlank).map(String::toInt))
        }

        var numberOfVisibleTrees = (2 * treeGrid.size + 2 * treeGrid.first().size) - 4

        for(i in 1 until treeGrid.lastIndex) {
            for(j in 1 until treeGrid[i].lastIndex) {
                if (isVisible(j, treeGrid[i]) || isVisible(i, treeGrid.map { it[j] })) {
                    numberOfVisibleTrees++
                }
            }
        }

        return numberOfVisibleTrees
    }

    fun part2(input: List<String>): Int {
        val treeGrid = mutableListOf<List<Int>>()
        input.forEach {
            treeGrid.add(it.split("").filterNot(String::isBlank).map(String::toInt))
        }

        var maxScenicScore = 0

        for(i in 1 until treeGrid.lastIndex) {
            for(j in 1 until treeGrid[i].lastIndex) {
                val currentTreeScenicScore = lineScenicScore(j, treeGrid[i]) * lineScenicScore(i, treeGrid.map { it[j] })
                maxScenicScore = max(currentTreeScenicScore, maxScenicScore)
            }
        }

        return maxScenicScore
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
