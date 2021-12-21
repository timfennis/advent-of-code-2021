package aoc.day21

import arrow.core.memoize
import kotlin.math.max

fun main (){
    val (a, b) = sim(5,10, 0, 0, true)
    println("p1 $a p2 $b best ${max(a, b)}")
}

val sim = ::simulate.memoize()

fun simulate(currentPlayer: Int, otherPlayer: Int, currentScore: Int, otherScore: Int, p1t: Boolean): Pair<Long, Long> {

    if (otherScore >= 21) {
        return if (p1t) {
            0L to 1L
        } else {
            1L to 0L
        }
    }

    return outcomes.map { outcome ->
        val v1 = (currentPlayer + outcome) % 10
        val v2 = if (v1 == 0) 10 else v1
        sim(otherPlayer, v2, otherScore, currentScore + v2, !p1t)
    }.fold(0L to 0L) { (x, y), (xx, yy) -> x + xx to y + yy }
}


private val outcomes = listOf(1,2,3).flatMap { x ->
    listOf(1,2,3).flatMap { y ->
        listOf(1,2,3).map { z ->
            listOf(x,y,z).sum()
        }
    }
}