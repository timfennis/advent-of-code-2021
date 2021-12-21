package aoc.day21

import arrow.core.foldLeft
import arrow.core.memoize
import kotlin.math.max

fun main (){
    val (a, b) = sim(5-1,10-1, 0, 0)
    println("p1 $a p2 $b best ${max(a, b)}")
}

// val sim = ::simulate.memoize()

fun sim(currentPos: Int, otherPos: Int, currentScore: Int, otherScore: Int): Pair<Long, Long> {

    if (currentScore >= 21) {
        return 1L to 0L
    } else if (otherScore >= 21) {
        return 0L to 1L
    }

    return outcomes.foldLeft(0L to 0L) { (x, y), (outcome, times) ->
        val newPos = (currentPos + outcome) % 10
        val (xx, yy) = sim(otherPos, newPos, otherScore, currentScore + newPos + 1)
        x + (yy * times) to y + (xx * times)
    }
}


private val outcomes = listOf(1,2,3).flatMap { x ->
    listOf(1,2,3).flatMap { y ->
        listOf(1,2,3).map { z ->
            listOf(x,y,z).sum()
        }
    }
}.groupBy { it }.mapValues { (_, v) -> v.size }