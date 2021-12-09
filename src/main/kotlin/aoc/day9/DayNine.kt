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
    .apply { println(this) }
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