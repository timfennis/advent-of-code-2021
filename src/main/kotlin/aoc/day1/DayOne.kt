package aoc.day1

fun main() {
    println("Solution: " + solveDayOnePartOne(dayOneInput) + ", 1448 expected")
    println("Solution: " + solveDayOnePartOne(solveDayOnePartTwo(dayOneInput)) + ", 1471 expected")
}

/**
 * [1,2,3].zipWithNext() == List(Pair(1,2), Pair(2,3))
 *
 * After pairing all values with the next value we can count the amount of times when a < b to get the final answer
 */
fun solveDayOnePartOne(input: List<Int>) = input.zipWithNext().count { it.first < it.second }

fun solveDayOnePartTwo(input: List<Int>) = input.windowed(3).map(Iterable<Int>::sum)
