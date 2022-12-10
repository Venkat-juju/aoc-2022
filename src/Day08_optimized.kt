import kotlin.math.max

//1825
//235200
fun main() {

    fun List<Int>.indexOfMax(): Int {
        var max = Integer.MIN_VALUE
        var maxIndex = -1
        forEachIndexed { index, value ->
            if (max <= value) {
                max = index
                maxIndex = index
            }
        }

        return maxIndex
    }

    fun isVisible(treeIndex: Int, treeLine: List<Int>, indexOfMax: Int): Boolean {
        val treeHeight = treeLine[treeIndex]
        var isVisibleOnTheStart = treeLine.slice(0 until treeIndex).all{it < treeHeight}
        var isVisibleOnTheEnd = treeLine.slice(treeIndex + 1 .. treeLine.lastIndex).all{it < treeHeight}
        return isVisibleOnTheStart || isVisibleOnTheEnd
    }

    fun lineScenicScore(treeIndex: Int, treeLine: List<Int>): Int {
        val leftTrees = treeLine.slice(treeIndex - 1 downTo 0)
        val rightTrees = treeLine.slice(treeIndex + 1 .. treeLine.lastIndex)
        val treeHeight = treeLine[treeIndex]

        var leftVisibleTrees = leftTrees.indexOfFirst { it >= treeHeight } + 1
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
            val maxIndexOfCurrentRow = treeGrid[i].indexOfMax()
            for(j in 1 until treeGrid[i].lastIndex) {
                val columnValues = treeGrid.map { it[j] }
                val maxIndexOfCurrentColumn = columnValues.indexOfMax()
                if (isVisible(j, treeGrid[i], maxIndexOfCurrentRow) || isVisible(i, treeGrid.map { it[j] }, maxIndexOfCurrentColumn)) {
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
