package aoc

import aoc.day10.DayTen
import aoc.day11.DayEleven
import aoc.day12.DayTwelve
import aoc.day13.DayThirteen
import java.io.File

private val days = listOf(
    DayTen(),
    DayEleven(),
    DayTwelve(),
    DayThirteen()
)


fun main () {
    runDay(days.last())
}

fun runDay(day: Day) {
    val exampleInput = File("input/day${day.number}_example").readText()
    val input = File("input/day${day.number}").readText()

    if (runExamplePart(day, 1, day.solvePartOne(exampleInput))) return
    println("Day[${day.number}] part 1: ${day.solvePartOne(input)}")

    if (runExamplePart(day, 2, day.solvePartTwo(exampleInput))) return

    println("Day[${day.number}] part 2: ${day.solvePartTwo(input)}")
}

private fun runExamplePart(day: Day, part: Int, solution: Long): Boolean {
    print("Day[${day.number}] example part $part: $solution")
    if (day.exampleSolution[part - 1] != solution) {
        println(" incorrect ${day.exampleSolution[part - 1]} expected")
        return true
    } else {
        println(" correct")
    }

    return false
}

abstract class Day(val number: Int) {
    open val exampleSolution = listOf(-1L, -1L)

    abstract fun solvePartOne(input: String): Long
    abstract fun solvePartTwo(input: String): Long
}