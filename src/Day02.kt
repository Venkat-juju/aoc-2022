fun main() {

    fun getScoreOfSelection(selection: String): Int {
        return when(selection) {
            "X" -> 1
            "Y" -> 2
            "Z" -> 3
            else -> 0
        }
    }

    fun getScore(choices: String): Int {

        // A - Rock, B - Paper, C - Scissors
        // X - Rock, Y - Paper, Z - Scissors
        // Rock wins Scissors, Scissors defeats Paper, Paper defeats Rock

        val opponentChoice = choices.split(" ").first()
        val myChoice = choices.split(" ").last()
        val isWin = (opponentChoice == "C" && myChoice == "X") || (opponentChoice == "A" && myChoice == "Y") || (opponentChoice == "B" && myChoice == "Z")
        val isDraw = (opponentChoice == "C" && myChoice == "Z") || (opponentChoice == "B" && myChoice == "Y") || (opponentChoice == "A" && myChoice == "X")

        return (if (isWin) 6 else if (isDraw) 3 else 0) + getScoreOfSelection(myChoice)
    }

    fun getMyChoice(opponentChoice: String, resultHasToBe: String): String {
        val needWin = resultHasToBe == "Z"
        val needDraw = resultHasToBe == "Y"

        return when(opponentChoice) {
            "A" -> {
                if (needWin) "Y" else if (needDraw) "X" else "Z"
            }
            "B" -> {
                if (needWin) "Z" else if (needDraw) "Y" else "X"
            }
            "C" -> {
                if (needWin) "X" else if (needDraw) "Z" else "Y"
            }
            else -> ""
        }
    }

    fun part1(input: List<String>): Int {
        var totalScore = 0

        input.forEach {
            totalScore += getScore(it)
        }

        return totalScore
    }

    fun part2(input: List<String>): Int {
        var totalScore = 0

        input.forEach {
            val opponentChoice = it.split(" ").first()
            val myChoice = getMyChoice(opponentChoice, it.split(" ").last())

            totalScore += getScore("$opponentChoice $myChoice")
        }

        return totalScore
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    println(part2(testInput))
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
