package aoc.day6

import arrow.core.memoize

fun solveRecursive(days: Int) = daySixInput.sumOf {
    memoizedCalcNFishies(days.toLong(), it.toLong())
}

val memoizedCalcNFishies = ::calcNFishies.memoize()

fun calcNFishies(nDays: Long, counter: Long): Long =
    if (nDays < 9) {
        if (counter < nDays) 1 else 0
    } else {
        1 + memoizedCalcNFishies(nDays - counter - 1, 6) + memoizedCalcNFishies(nDays - counter - 1, 8)
    }

