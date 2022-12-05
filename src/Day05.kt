import java.util.Stack

fun main() {

    fun processCommand(command: String): Triple<Int, Int, Int> {
        val splits = command.split(" ")
        return Triple(splits[1].toInt(), splits[3].toInt(), splits[5].toInt())
    }

    fun part1(inputs: List<String>, stackList: List<Stack<Char>>): String {
        inputs.forEach {input ->
            val (count, from, to) = processCommand(input)
//            println("moving $count from $from to $to")
            repeat(count) {
//                println("current count in ${from} is ${stackList[from-1].count()}")
                val element = stackList[from - 1].pop()
                stackList[to - 1].push(element)
            }
        }
        var result = ""
        stackList.forEach { stack ->
            result += stack.peek()
        }

        return result
    }

    fun part2(inputs: List<String>, stackList: List<Stack<Char>>): String {

        stackList.forEach { println(it) }

        inputs.forEach {input ->
            val (count, from, to) = processCommand(input)
//            println("before operation: count in ${from} is  ${stackList[from-1].count()} and in $to is ${stackList[to-1].count()}")
//            println("moving $count from $from to $to")
            var element = ""
            repeat(count) { element += stackList[from - 1].pop() }
            element.reversed().forEach { stackList[to - 1].push(it) }
//            println("after operation: count in ${from} is ${stackList[from-1].count()} and in $to is  ${stackList[to-1].count()}")
        }
        var result = ""
        stackList.forEach { stack ->
            result += stack.peek()
        }

        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    val testStackList = mutableListOf<Stack<Char>>().apply {
        add(Stack<Char>().apply {
            push('Z')
            push('N')
        })
        add(Stack<Char>().apply {
            push('M')
            push('C')
            push('D')
        })
        add(Stack<Char>().apply {
            push('P')
        })
    }
    val part1StackList = mutableListOf<Stack<Char>>().apply {
        add(Stack<Char>().apply {
            push('W')
            push('D')
            push('G')
            push('B')
            push('H')
            push('R')
            push('V')
        })
        add(Stack<Char>().apply {
            push('J')
            push('N')
            push('G')
            push('C')
            push('R')
            push('F')
        })
        add(Stack<Char>().apply {
            push('L')
            push('S')
            push('F')
            push('H')
            push('D')
            push('N')
            push('J')
        })
        add(Stack<Char>().apply {
            push('J')
            push('D')
            push('S')
            push('V')
        })
        add(Stack<Char>().apply {
            push('S')
            push('H')
            push('D')
            push('R')
            push('Q')
            push('W')
            push('N')
            push('V')
        })
        add(Stack<Char>().apply {
            push('P')
            push('G')
            push('H')
            push('C')
            push('M')
        })
        add(Stack<Char>().apply {
            push('F')
            push('J')
            push('B')
            push('G')
            push('L')
            push('Z')
            push('H')
            push('C')
        })
        add(Stack<Char>().apply {
            push('S')
            push('J')
            push('R')
        })
        add(Stack<Char>().apply {
            push('L')
            push('G')
            push('S')
            push('R')
            push('B')
            push('N')
            push('V')
            push('M')
        })
    }
//    check(part1(testInput, testStackList) == "CMZ")
    check(part2(testInput, testStackList) == "MCD")

    val input = readInput("Day05")
//    println(part1(input, part1StackList))
    println(part2(input, part1StackList))

}
