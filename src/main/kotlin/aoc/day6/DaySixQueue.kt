package aoc.day6

import kotlin.collections.ArrayDeque

private val fishies = daySixInput
    .groupBy { it }
    .mapValues { it.value.size.toLong() }

fun createFishQueue() = ArrayDeque<Long>().apply {
    for (i in (0..8)) {
        this.add(fishies[i] ?: 0)
    }
}

fun solveWithQueue(days: Int): Long = solveDays(createFishQueue(), days).sum()

tailrec fun solveDays(fish: ArrayDeque<Long>, days: Int): ArrayDeque<Long> =
    if (days == 0) {
        fish
    } else {
        val zero = fish.removeFirst()
        fish[6] = fish[6] + zero
        fish.add(zero)
        solveDays(fish, days -1)
    }
