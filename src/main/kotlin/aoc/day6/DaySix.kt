package aoc.day6

fun main() {

    println(recOptimized(daySixInput
        .groupBy { it }
        .mapValues { it.value.size.toLong() }, 256)
        .map { it.value }
        .sum())
}

tailrec fun recOptimized(fish: Map<Int, Long>, days: Int): Map<Int, Long> =
    if (days == 0) {
        fish
    } else {
        recOptimized(
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
        fish;
    } else {
        recOriginal(fish.flatMap(::grow), days - 1)
    }

fun grow(n: Int): List<Int> =
    if (n == 0) {
        listOf(6, 8)
    } else {
        listOf(n - 1)
    }
