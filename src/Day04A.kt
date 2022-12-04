import kotlin.system.measureNanoTime

data class SectionRangeB(
    val startSection : Int,
    val endSection: Int
)

infix fun IntRange.contains(other: IntRange): Boolean {
    return other.first >= this.first && other.last <= this.last
}

infix fun SectionRangeB.isFullyContainedB(other: SectionRangeB): Boolean {
    return this.startSection..this.endSection contains other.startSection .. other.endSection ||
            other.startSection .. other.endSection contains this.startSection..this.endSection
}

infix fun SectionRangeB.isAtleastSingleSectionOverlapB(other: SectionRangeB): Boolean {
    return this.startSection in other.startSection.. other.endSection || other.startSection in this.startSection .. this.endSection
}

fun String.toSectionRangeB(): List<SectionRangeB> {
    val (firstSectionStart, firstSectionEnd, secondSectionStart, secondSectionEnd) = this.split(',', '-').map(String::toInt)
    return listOf(SectionRangeB(firstSectionStart, firstSectionEnd), SectionRangeB(secondSectionStart, secondSectionEnd))
}

fun main() {

    fun part1(input: List<String>): Int {

        return input.map(String::toSectionRangeB).count { it.first() isFullyContainedB it.last() }
    }

    fun part2(input: List<String>): Int {

        return input.map(String::toSectionRangeB).count { it.first() isAtleastSingleSectionOverlapB it.last() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    val timeTake = measureNanoTime {
        println(part1(input))
        println(part2(input))
    }
    println("Time taken: $timeTake ns")

}
