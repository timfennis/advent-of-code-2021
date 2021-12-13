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
            println((0..5).joinToString("\n") { y ->
                (0..40).joinToString("") { x ->
                    if (it.contains(Point(x, y))) "█" else " "
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
    set.fold(emptySet<Point>()) { points, point -> points + point.foldOver(fold) }

data class InputData(val points: Set<Point>, val folds: List<Fold>)
data class Fold(val direction: Char, val coordinate: Int)
data class Point(val x: Int, val y: Int) {

    fun foldOver(fold: Fold): Set<Point> = if (fold.direction == 'x') {
        if (fold.coordinate == this.x) {
            // This case never actually happens on the input data
            emptySet()
        } else {
            if (this.x > fold.coordinate) {
                setOf(Point(fold.coordinate - (this.x - fold.coordinate), this.y))
            } else {
                setOf(this)
            }
        }
    } else {
        if (fold.coordinate == this.y) {
            // This case never actually happens on the input data
            emptySet()
        } else {
            if (this.y > fold.coordinate) {
                setOf(Point(this.x, fold.coordinate - (this.y - fold.coordinate)))
            } else {
                setOf(this)
            }
        }
    }
}
