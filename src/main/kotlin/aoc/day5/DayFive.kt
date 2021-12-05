package aoc.day5

import java.util.ArrayList
import kotlin.math.max

fun main ()
{
    // In the initial version I tried to resize the grid every time a line was added that wasn't in bounds
    // but this gave me a headache
    val width = dayFiveInput.maxByOrNull { max(it.x1, it.x2) }?.let { max(it.x1, it.x2) + 1 } ?: 10
    val height = dayFiveInput.maxByOrNull { max(it.y1, it.y2) }?.let { max(it.y1, it.y2) + 1 } ?: 10

    // In the lines below MutableGrid can be substituted for Grid which is slower but purely functional.
    // Make sure to also check DayFiveOptimized.kt because it has a much simpler implementations of the same problem

    // Part one
    val grid1 = dayFiveInput
        .filterNot{ it.isDiagonal() }
        .fold(MutableGrid(width, height)) { acc, line -> acc.withLine(line) }

    println(grid1.count { it >= 2 })

    // Part two
    val grid2 = dayFiveInput.fold(MutableGrid(width, height)) { acc, line -> acc.withLine(line) }
    println(grid2.count { it >= 2 })
}


fun emptyArrayList(w: Int, h: Int): ArrayList<Int> = ArrayList((0 until w*h).map { 0 })

