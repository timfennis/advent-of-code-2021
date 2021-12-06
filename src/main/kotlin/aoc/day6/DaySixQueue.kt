package aoc.day6

import kotlin.collections.ArrayDeque


private val fishies = daySixInput
    .groupBy { it }
    .mapValues { it.value.size.toLong() }

fun createFishQueue() = ArrayDeque<Long>().apply {
    addAll(listOf(0,0,0,0,0,0,0,0,0))
    for (i in (0..8)) {
        this[i] = fishies[i] ?: 0
    }
}

fun solveWithQueue(): Long {
    return recOptimized(createFishQueue(), 256).sum()
}

tailrec fun recOptimized(fish: ArrayDeque<Long>, days: Int): ArrayDeque<Long> =
    if (days == 0) {
        fish
    } else {
        val zero = fish.removeFirst()
        fish[6] = fish[6] + zero
        fish.add(zero)

        recOptimized(fish, days -1)
    }
