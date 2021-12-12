package aoc.day9

fun main() {
    println(dayNineExampleInput)
    println(solvePartOne(dayNineExampleInput))
    println(solvePartTwo(dayNineExampleInput))

    println(dayNineInput)
    println(solvePartOne(dayNineInput))
    println(solvePartTwo(dayNineInput))
}

fun solvePartOne(input: Grid) = findLowPoints(input).sumOf { it.second + 1 }
fun solvePartTwo(input: Grid) = findLowPoints(input)
    .map { calculateBasinSize(input, setOf(it.first)) }
    .sortedDescending()
    .also { println(it) }
    .take(3)
    .reduce { acc, i -> acc * i }

fun findLowPoints(input: Grid) = input.rows
    .foldIndexed(listOf<Pair<Int, Int>>()) { currentIndex, lowPoints, currentValue ->
        if (currentValue < input.getNeighbours(currentIndex).minOf { it }) {
            lowPoints + listOf(currentIndex to currentValue)
        } else {
            lowPoints
        }
    }


tailrec fun calculateBasinSize(input: Grid, offsetsInBasin: Set<Int>): Int {
    val newOffsets = offsetsInBasin
        .flatMap { listOf(it) + input.getNeighbourOffsets(it) }
        .filterNot { input.rows[it] == 9 }
        .toSet()

    return if (offsetsInBasin.size == newOffsets.size) {
        return newOffsets.size
    } else {
        calculateBasinSize(input, newOffsets)
    }
}

data class Grid(val width: Int, val height: Int, val rows: List<Int>) {
    fun getNeighbours(currentIndex: Int) = listOf(
        if (currentIndex % width == 0) 9 else rows.getOrNull(currentIndex - 1) ?: 9,
        if (currentIndex % width == (width - 1)) 9 else rows.getOrNull(currentIndex + 1) ?: 9,
        rows.getOrNull(currentIndex - width) ?: 9,
        rows.getOrNull(currentIndex + width) ?: 9,
    )

    fun getNeighbourOffsets(currentIndex: Int) = listOfNotNull(
        if (currentIndex % width == 0) null else currentIndex - 1,
        if (currentIndex % width == (width - 1)) null else currentIndex + 1,
        if (currentIndex > width) currentIndex - width else null,
        if (currentIndex + width < rows.size) currentIndex + width else null,
    )
}