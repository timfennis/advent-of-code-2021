package aod.day1.optimized

import aoc.day1.dayOneInput

fun main() {
    // part 1
    println(countIncreases(dayOneInput))
    // Voor part 2 kun je part 1 hergebruiken
    println(countIncreases(sumSlidingTotal(dayOneInput)))
}


/**
 * fold over de input (min de eerste twee elementen)
 * sum het huidige element met de twee vorige
 */
fun sumSlidingTotal(input: List<Int>) =
    input.dropLast(2).fold(emptyList<Int>()) { acc, current -> acc + listOf(current + input[acc.size + 1] + input[acc.size + 2]) }


/**
 * fold (met index) over de input min het eerste element
 * als het huidige element lager is dan de vorige tel 1 op bij de accumulator
 * index wijst altijd naar de vorige omdat we het eerste element gedropt hebben
 */
fun countIncreases(input: List<Int>): Int =
    input.drop(1).foldIndexed(0) { index, acc, current -> if (input[index] < current) acc + 1 else acc }