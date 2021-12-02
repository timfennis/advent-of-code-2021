package aoc.day1

fun main() {
    println(solveDayOnePartOne(dayOneInput))
    println(solveDayOnePartOne(solveDayOnePartTwo(dayOneInput)))
}

/**
 * [1,2,3].zipWithNext() == List(Pair(1,2), Pair(2,3))
 *
 * After pairing all values with the next value we can count the amount of times when a < b to get the final answer
 */
fun solveDayOnePartOne(input: List<Int>) = input.zipWithNext().count { it.first < it.second }

/**
 * Applying zipWithNext twice and discarding the second pair first value (b.first) essentially gives us the sliding window we need.
 */
fun solveDayOnePartTwo(input: List<Int>) = input.zipWithNext().zipWithNext { a, b -> a.first + a.second + b.second }
