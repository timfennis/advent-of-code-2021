package aoc.day7

import arrow.core.memoize
import kotlin.math.max
import kotlin.math.min


fun main() {
    println(solve(examplePositions, ::calcPartOneCost))
    println(solve(daySevenPositions, ::calcPartOneCost))

    println(solve(examplePositions, ::calcPartTwoCost))
    println(solve(daySevenPositions, ::calcPartTwoCost))
}

fun solve(input: List<Int>, calculator: (List<Int>, Int) -> Int) =
    (input.minOf { it }..input.maxOf { it })
        .map { it to calculator(input, it) }
        .minByOrNull { (_, cost) -> cost }

fun calcPartOneCost(input: List<Int>, position: Int) =
    input.sumOf { max(it, position) - min(it, position) }

fun calcPartTwoCost(input: List<Int>, position: Int) =
    input.sumOf { triangleNumber(max(it, position) - min(it, position)) }

fun triangleNumber(n: Int) = ((n * n) + n) / 2

/**
 * Originally I had created this tiny beast (that was really slow without memoization)
 * but after googling for "Factorial with addition" I found the triangleNumber formula
 */
val factorialButWithAddition = ::_factorialButWithAddition.memoize()
fun _factorialButWithAddition(n: Int): Int = if (n == 0) 0 else n + _factorialButWithAddition(n - 1)