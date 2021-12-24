package aoc.day23

import aoc.Day

class DayTwentyThree : Day(23) {

    override val exampleSolution = listOf(12521L, 44169L)

    override fun solvePartOne(input: String) = aoc.day23.part1.solve(input)

    override fun solvePartTwo(input: String) = aoc.day23.part2.solve(input)
}