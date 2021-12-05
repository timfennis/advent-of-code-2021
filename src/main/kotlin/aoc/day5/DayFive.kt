package aoc.day5

import java.util.ArrayList
import kotlin.math.max

fun main ()
{
    val width = dayFiveInput.maxByOrNull { max(it.x1, it.x2) }?.let { max(it.x1, it.x2) + 1 } ?: 10
    val height = dayFiveInput.maxByOrNull { max(it.y1, it.y2) }?.let { max(it.y1, it.y2) + 1 } ?: 10

    // Part one
    val grid1 = dayFiveInput.filterNot{ it.isDiagonal() }.fold(MutableGrid(width, height)) { acc, line -> acc.withLine(line) }
    println(grid1.count { it >= 2 })

    // Part two
    val grid2 = dayFiveInput.fold(MutableGrid(width, height)) { acc, line -> acc.withLine(line) }
    println(grid2.count { it >= 2 })
}


fun emptyArrayList(w: Int, h: Int): ArrayList<Int> = ArrayList((0 until w*h).map { 0 })

