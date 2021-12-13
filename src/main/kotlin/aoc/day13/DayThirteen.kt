package aoc.day13

import aoc.Day
import aoc.splitLines

class DayThirteen : Day(13) {
    override val exampleSolution = listOf(17, -1L)

    override fun solvePartOne(input: String) = input.split("\n\n")
        .let { InputData(it.first().toPoints(), it.last().toFolds()) }
        .let {
            it.folds
                .take(1)
                .fold(it.points) { points, fold -> applyFold(points, fold) }
        }.size.toLong()

    override fun solvePartTwo(input: String) = input.split("\n\n")
        .let { InputData(it.first().toPoints(), it.last().toFolds()) }
        .let {
            it.folds.fold(it.points) { points, fold -> applyFold(points, fold) }

        }
        .also {
            val ym = it.maxOf { p -> p.y }
            val xm = it.maxOf { p -> p.x }
            println((0..ym).joinToString("\n") { y ->
                (0..xm).joinToString("") { x ->
                    if (it.contains(Point(x, y))) "█" else "░"
                }
            })
        }
        .let { -1L }
}

private fun String.toPoints() =
    this.splitLines().map { Point(it.split(',').first().toInt(), it.split(',').last().toInt()) }.toSet()

private fun String.toFolds() =
    this.splitLines().map { Fold(it.split('=').first().last(), it.split('=').last().toInt()) }

private fun applyFold(set: Set<Point>, fold: Fold) =
    set.fold(emptySet<Point>()) { points, point -> points + point.foldedOver(fold) }

data class InputData(val points: Set<Point>, val folds: List<Fold>)
data class Fold(val direction: Char, val coordinate: Int) {
    fun flipped() = Fold(if (direction == 'x') 'y' else 'x', coordinate)
}

data class Point(val x: Int, val y: Int) {
    fun foldedOver(fold: Fold): Point = if (fold.direction == 'x') {
        if (this.x > fold.coordinate) {
            Point(fold.coordinate - (this.x - fold.coordinate), this.y)
        } else {
            this
        }
    } else {
        this.flipped().foldedOver(fold.flipped()).flipped()
    }

    fun flipped() = Point(y, x)
}
