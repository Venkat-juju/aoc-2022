data class SectionRange(
    val startSection : Int,
    val endSection: Int
)

fun SectionRange.isFullyContained(other: SectionRange): Boolean {
    return (this.startSection >= other.startSection && this.endSection <= other.endSection) ||
            (this.startSection <= other.startSection && this.endSection >= other.endSection)
}

fun SectionRange.isAtleastSingleSectionOverlap(other: SectionRange): Boolean {
    return ((this.startSection..this.endSection) intersect (other.startSection .. other.endSection)).isNotEmpty()
}

fun String.toSectionRange(): List<SectionRange> {
    return this.split(",").map { it.split("-") }.map { SectionRange(it.first().toInt(), it.last().toInt()) }
}

fun main() {

    fun part1(input: List<String>): Int {
        return input.map(String::toSectionRange).count { it.first().isFullyContained(it.last()) }
    }

    fun part2(input: List<String>): Int {

        return input.map(String::toSectionRange).count { it.first().isAtleastSingleSectionOverlap(it.last()) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
