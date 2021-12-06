package aoc.day6

import aoc.simpleMeasureTest

fun main() {
    val days = 256

    simpleMeasureTest(iterations = 1000, testCount = 10, warmCount = 2, prefix = "Immutable Map") {
        solveWithMap(days)
    }

    println("Immutable Map: " + solveWithMap(days))

    simpleMeasureTest(iterations = 1000, testCount = 10, warmCount = 2, prefix = "ArrayDeque") {
        solveWithQueue(days)
    }

    println("ArrayDeque: " + solveWithQueue(days))

    simpleMeasureTest(iterations = 1000, testCount = 10, warmCount = 2, prefix = "Recursion") {
        solveRecursive(days)
    }

    println("Recursion: " + solveRecursive(days))
}

fun solveWithMap(days: Int) = solveDays(daySixInput
    .groupBy { it }
    .mapValues { it.value.size.toLong() }, days)
    .map { it.value }
    .sum()

tailrec fun solveDays(fish: Map<Int, Long>, days: Int): Map<Int, Long> =
    if (days == 0) {
        fish
    } else {
        solveDays(
            mapOf(
                0 to (fish[1] ?: 0),
                1 to (fish[2] ?: 0),
                2 to (fish[3] ?: 0),
                3 to (fish[4] ?: 0),
                4 to (fish[5] ?: 0),
                5 to (fish[6] ?: 0),
                6 to (fish[0] ?: 0) + (fish[7] ?: 0),
                7 to (fish[8] ?: 0),
                8 to (fish[0] ?: 0)
            ), days - 1
        )
    }

/**
 * Leaving old version here for the memes
 * It ran for a few minutes and then I got bored.
 */

tailrec fun recOriginal(fish: List<Int>, days: Int): List<Int> =
    if (days == 0) {
        fish
    } else {
        recOriginal(fish.flatMap(::grow), days - 1)
    }

fun grow(n: Int): List<Int> =
    if (n == 0) {
        listOf(6, 8)
    } else {
        listOf(n - 1)
    }
