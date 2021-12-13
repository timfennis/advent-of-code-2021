package aoc.day13

import aoc.Day
import aoc.splitLines

typealias Point = Pair<Int, Int>

class DayThirteen : Day(13) {
    override val exampleSolution = listOf(17, -1L)

    override fun solvePartOne(input: String) = input.split("\n\n")
        .let { InputData(it.first().toPoints(), it.last().toFolds()) }
        .let {
            it.folds
                .take(1)
                .fold(it.points) { points, fold -> points.applyFold(fold) }
        }.toSet().size.toLong()

    override fun solvePartTwo(input: String) = input.split("\n\n")
        .let { InputData(it.first().toPoints(), it.last().toFolds()) }
        .let {
            it.folds.fold(it.points) { points, fold -> points.applyFold(fold) }

        }
        .also { println(it) }
        .also { (0..5).forEach { y -> (0..40).forEach { x -> print(if (it.contains(x to y)) 'X' else '.') }; println() } }
        .let { -1L }
        .toLong()
}

private fun String.toPoints() =
    this.splitLines().map { it.split(',').first().toInt() to it.split(',').last().toInt() }

private fun String.toFolds() =
    this.splitLines().map { Fold(it.split('=').first().last(), it.split('=').last().toInt()) }

private fun List<Point>.applyFold(fold: Fold) =
    this.fold(emptyList<Point>()) { points, point -> points + point.foldOver(fold) }

data class InputData(val points: List<Point>, val folds: List<Fold>)
data class Fold(val direction: Char, val coordinate: Int)

fun Point.foldOver(fold: Fold) = if (fold.direction == 'x') {
    if (fold.coordinate == this.first) {
        // This case never actually happens on the input data
        emptyList()
    } else {
        if (this.first > fold.coordinate) {
            listOf(Pair(fold.coordinate - (this.first - fold.coordinate), this.second))
        } else {
            listOf(this)
        }
    }
} else {
    if (fold.coordinate == this.second) {
        // This case never actually happens on the input data
        emptyList()
    } else {
        if (this.second > fold.coordinate) {
            listOf(Pair(this.first, fold.coordinate - (this.second - fold.coordinate)))
        } else {
            listOf(this)
        }
    }
}