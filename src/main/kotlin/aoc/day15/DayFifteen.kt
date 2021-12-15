package aoc.day15

import aoc.Day
import aoc.splitLines

typealias Point = Pair<Int, Int>

class DayFifteen : Day(15) {
    override val exampleSolution = listOf(40, 315L)

    override fun solvePartOne(input: String) =
        solveRecursive(parseInput(input), mapOf((0 to 0) to 0))
            .toLong()

    override fun solvePartTwo(input: String) =
        solveRecursive(enlargeMap(parseInput(input)), mapOf((0 to 0) to 0))
            .toLong()
}

private fun enlargeMap(ys: List<List<Int>>): List<List<Int>> {
    val something = ys.map { xs -> (0..4).fold(emptyList<Int>()) { acc, i -> acc + enlargeRowTimes(xs, i) } }

    return (0..4).fold(emptyList()) { acc, i -> acc + something.map { enlargeRowTimes(it, i) } }
}

private tailrec fun enlargeRowTimes(xs: List<Int>, times: Int): List<Int> = when (times) {
    0 -> xs
    1 -> enlargeRow(xs)
    else -> enlargeRowTimes(enlargeRow(xs), times - 1)
}

private fun enlargeRow(xs: List<Int>) = xs.map { new ->
    when (new + 1) {
        10 -> 1
        else -> new + 1
    }
}

private fun parseInput(input: String) = input.splitLines()
    .map { it.map { char -> char.digitToInt() } }

private tailrec fun solveRecursive(input: List<List<Int>>, grid: Map<Point, Int>): Int {
    val w = input.first().size
    val h = input.size

    val newGrid = grid
        .flatMap { (point, cost) ->
            getNeighbours(
                x = point.first,
                y = point.second,
                input.first().size,
                input.size
            ).map { (x, y) -> (x to y) to input[y][x] + cost } + listOf(point to cost)
        }
        .groupBy { it.first }
        .mapValues { (_, value) -> value.minOf { pp -> pp.second } }
        .toMap()

    if (newGrid == grid) {
        return grid[(h - 1) to (w - 1)]!!
    }

    return solveRecursive(input, newGrid)
}

private fun getNeighbours(x: Int, y: Int, w: Int, h: Int) = listOf(
    x - 1 to y,
    x to y - 1,
    x + 1 to y,
    x to y + 1
).filter { (x, y) -> x >= 0 && y >= 0 && x < w && y < h }