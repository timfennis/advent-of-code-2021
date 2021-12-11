package aoc.day11

import aoc.Day
import aoc.splitLines

class DayEleven : Day(11) {

    override val exampleSolution = listOf(1656L, 195L)

    override fun solvePartOne(input: String): Long = simulateTimes(Data(0L, parseInput(input)), 100).flashes

    override fun solvePartTwo(input: String): Long = simulateUntilSynchronized(Data(0L, parseInput(input))).steps.toLong()

}


private fun parseInput(input: String): List<List<Int>> =
    input.splitLines().map { line -> line.map { char -> char.digitToInt() } }

private fun simulateTimes(data: Data, steps: Int): Data = (0 until steps).fold(data) { d, _ -> d.stepAll(); d }
private tailrec fun simulateUntilSynchronized(data: Data): Data = if (data.isSynchronized()) {
    data
} else {
    data.stepAll()
    simulateUntilSynchronized(data)
}

class Data(var flashes: Long, grid: List<List<Int>>) {
    private val grid = grid.map { it.toMutableList() }.toMutableList()
    var steps = 0

    // fun incremented() = Grid(grid.map { xs -> xs.map { v -> v + 1 } })

    fun stepAll() {
        steps++

        // Increment all
        grid.forEachIndexed { y, xs -> xs.forEachIndexed { x, _ -> grid[y][x]++ } }

        // Flash all
        grid.forEachIndexed { y, xs -> xs.forEachIndexed { x, _ -> flash(x, y) } }

        // Cap zero
        grid.forEachIndexed { y, xs -> xs.forEachIndexed { x, _ -> if (grid[y][x] > 9) grid[y][x] = 0 } }
    }

    fun isSynchronized() = grid.flatten().all { grid.first().first() == it }

    private fun inc(x: Int, y: Int) {
        grid.getOrNull(y)?.getOrNull(x)?.let {
            grid[y][x]++
        }
    }

    private fun flash(x: Int, y: Int) {
        grid.getOrNull(y)?.getOrNull(x)?.let {
            if (grid[y][x] in 10..98) {
                grid[y][x] = 99
                flashes++

                inc(x - 1, y - 1);
                inc(x, y - 1);
                inc(x + 1, y - 1);

                inc(x - 1, y);
                //  Middle value remains
                inc(x + 1, y);

                inc(x - 1, y + 1);
                inc(x, y + 1);
                inc(x + 1, y + 1);

                flash(x - 1, y - 1);
                flash(x, y - 1);
                flash(x + 1, y - 1);

                flash(x - 1, y);
                //  Middle value remains
                flash(x + 1, y);

                flash(x - 1, y + 1);
                flash(x, y + 1);
                flash(x + 1, y + 1);
            }
        }
    }

    override fun toString() = grid.joinToString("\n") { xs -> xs.joinToString("") }
}