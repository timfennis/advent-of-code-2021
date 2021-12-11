@file:Suppress("SameParameterValue")

package aoc.day11

import aoc.Day
import aoc.splitLines
import aoc.updated

class DayEleven : Day(11) {

    override val exampleSolution = listOf(1656L, 195L)

    override fun solvePartOne(input: String): Long = simulateTimes(Data(0L, 0L, parseInput(input)), 100).flashes

    override fun solvePartTwo(input: String): Long =
        simulateUntilSynchronized(Data(0L, 0L, parseInput(input))).steps
}

private fun parseInput(input: String): List<List<Int>> =
    input.splitLines().map { line -> line.map { char -> char.digitToInt() } }

private fun simulateTimes(data: Data, steps: Int): Data =
    (0 until steps).fold(data) { d, _ -> d.simulateOneGeneration() }

private tailrec fun simulateUntilSynchronized(data: Data): Data = if (data.isSynchronized()) {
    data
} else {
    simulateUntilSynchronized(data.simulateOneGeneration())
}

data class Data(val flashes: Long, val steps: Long, val grid: List<List<Int>>) {

    fun simulateOneGeneration() = this.incremented().flashed().copy(steps = steps + 1)

    fun isSynchronized() = grid.flatten().all { grid.first().first() == it }

    private fun incremented() = Data(this.flashes, this.steps, grid.map { xs -> xs.map { v -> v + 1 } })

    private fun flashed(): Data =
        coordinates().fold(this) { me, (x, y) -> if (me.grid[y][x] >= 10) me.flashAt(x, y) else me }

    private fun flashAt(x: Int, y: Int): Data = Data(
        flashes = flashes + 1,
        steps = steps,
        grid = neighbours(x, y)
            .filterNot { (xx, yy) ->  grid[yy][xx] == 0 }
            .fold(grid.updated(x, y, 0)) { grid, (xx, yy) -> grid.incremented(xx, yy) }
    ).flashed()

    private fun coordinates() = (grid.indices).map { y -> (grid.indices).map { x -> x to y } }.flatten()

    override fun toString() =
        "[Generation: $steps, Flashes: $flashes, Hash: ${this.hashCode()}]\n" + grid.joinToString("\n") { xs -> xs.joinToString("") }
}

private fun neighbours(x: Int, y: Int) = listOf(
    x - 1 to y - 1,
    x to y - 1,
    x + 1 to y - 1,

    x - 1 to y,
    // Exclude the element itself
    // x to y,
    x + 1 to y,

    x - 1 to y + 1,
    x to y + 1,
    x + 1 to y + 1,
).filter { (x, y) -> x in (0 until 10) && y in (0 until 10) }

fun List<List<Int>>.updated(x: Int, y: Int, value: Int) = this.updated(y, this[y].updated(x, value))
fun List<List<Int>>.incremented(x: Int, y: Int) = this.updated(y, this[y].updated(x, this[y][x] + 1))