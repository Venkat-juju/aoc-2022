import java.lang.Math.abs

fun main() {

    fun isAdjacentPoint(firstPoint: Pair<Int, Int>, secondPoint: Pair<Int, Int>): Boolean {
        val distanceRowWise = abs(secondPoint.first - firstPoint.first)
        val distanceColumnWise = abs(secondPoint.second - firstPoint.second)

        return (distanceRowWise == 1 && distanceColumnWise == 1) ||
                (distanceRowWise == 1 && distanceColumnWise == 0) ||
                (distanceColumnWise == 1 && distanceRowWise == 0) ||
                (distanceRowWise == 0 && distanceColumnWise == 0)
    }

    fun getNewPositionAfterMove(currentPosition: Pair<Int, Int>, direction: String): Pair<Int, Int> {
        return when(direction) {
            "R" -> Pair(currentPosition.first + 1, currentPosition.second)
            "L" -> Pair(currentPosition.first - 1, currentPosition.second)
            "U" -> Pair(currentPosition.first, currentPosition.second - 1)
            "D" -> Pair(currentPosition.first, currentPosition.second + 1)
            else -> error("Invalid input")
        }
    }

    fun getNewTailPositionToNearHeadPosition(tailPosition: Pair<Int, Int>, headPosition: Pair<Int, Int>): Pair<Int, Int> {
        val isSameRow = (headPosition.second - tailPosition.second) == 0
        val isSameColumn = (headPosition.first - tailPosition.first) == 0

        if (isSameRow) {
            return if (isAdjacentPoint(Pair(tailPosition.first+1, tailPosition.second), headPosition))
                Pair(tailPosition.first+1, tailPosition.second)
            else
                Pair(tailPosition.first-1, tailPosition.second)
        } else if (isSameColumn) {
            return if(isAdjacentPoint(Pair(tailPosition.first, tailPosition.second-1), headPosition))
                Pair(tailPosition.first, tailPosition.second-1)
            else
                Pair(tailPosition.first, tailPosition.second+1)
        } else {
            val isHeadIsDownToTheTailNode = headPosition.second - tailPosition.second > 0
            val isHeadIsLeftToTheTailNode = headPosition.first - tailPosition.first < 0

            return if (isHeadIsDownToTheTailNode) {
                if(isHeadIsLeftToTheTailNode) {
                    Pair(tailPosition.first-1, tailPosition.second+1)
                } else {
                    Pair(tailPosition.first+1, tailPosition.second+1)
                }
            } else {
                if (isHeadIsLeftToTheTailNode) {
                    Pair(tailPosition.first-1, tailPosition.second-1)
                } else {
                    Pair(tailPosition.first+1, tailPosition.second-1)
                }
            }
        }
    }

    fun part1(inputs: List<String>): Int {
        val travelledPoints = mutableSetOf<Pair<Int, Int>>()
        var currentTailPosition = Pair(0, 0)
        var currentHeadPosition = Pair(0, 0)
        var previousHeadPosition = Pair(0, 0)

        travelledPoints.add(currentTailPosition)

        inputs.forEach { input ->
            val (direction, steps) = input.split(" ")
            repeat(steps.toInt()) {
                currentHeadPosition = getNewPositionAfterMove(currentHeadPosition, direction)
                if (!isAdjacentPoint(currentTailPosition, currentHeadPosition)) {
                    currentTailPosition = getNewTailPositionToNearHeadPosition(currentTailPosition, currentHeadPosition)
                    travelledPoints.add(currentTailPosition)
                }
                previousHeadPosition = currentHeadPosition
            }
        }

        return travelledPoints.size
    }

    fun part2(inputs: List<String>): Int {
        val travelledPoints = mutableSetOf<Pair<Int, Int>>()
        var previousKnotPositions = mutableListOf<Pair<Int, Int>>().apply {
            repeat(10) {
                add(Pair(0,0))
            }
        }
        var currentKnotPositions = mutableListOf<Pair<Int, Int>>().apply {
            repeat(10) {
                add(Pair(0,0))
            }
        }

        travelledPoints.add(currentKnotPositions.first())

        inputs.forEach { input ->
            val (direction, numOfSteps) = input.split(" ")
//            println("move $direction $numOfSteps times")
            repeat(numOfSteps.toInt()) {
//                println(direction)
                currentKnotPositions[0] = getNewPositionAfterMove(currentKnotPositions[0], direction)
                currentKnotPositions.forEachIndexed { index, knot ->
                    if (index < currentKnotPositions.size - 1 && !isAdjacentPoint(knot, currentKnotPositions[index + 1])) {
                        currentKnotPositions[index+1] = getNewTailPositionToNearHeadPosition(currentKnotPositions[index+1], knot)
                    }
                }
//                println(currentKnotPositions)
                travelledPoints.add(currentKnotPositions.last())
                previousKnotPositions.clear()
                previousKnotPositions.addAll(currentKnotPositions)
            }
//            println(currentKnotPositions)
        }

        return travelledPoints.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    println(part1(testInput)) // 13 with test input 1
    println(part2(testInput)) // 36 with test input 2

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}

/**
 * test input for part1
 * R 4
 * U 4
 * L 3
 * D 1
 * R 4
 * D 1
 * L 5
 * R 2
 */

/**
 * test input for part2
 * R 5
 * U 8
 * L 8
 * D 3
 * R 17
 * D 10
 * L 25
 * U 20
 */
