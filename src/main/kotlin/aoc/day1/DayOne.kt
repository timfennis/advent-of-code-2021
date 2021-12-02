package aoc.day1

fun main() {
    println(solveDayOne(solveDayOnePartTwo(dayOneInput)))
}

/**
 * Oplossing voor part 2: met tussenstappen
 */
fun solveDayOnePartTwo(input: List<Int>) =
    input.fold(emptyList<Triple<Int?, Int?, Int?>>()) { acc, current -> acc + listOf(Triple(current, input.getOrNull(acc.size + 1), input.getOrNull(acc.size + 2))) }
        .filterNot { it.toList().any { num -> num == null } }
        .map { triple -> triple.toList().sumOf { it ?: 0 } }

/**
 * Oplossing voor part 1
 */
fun solveDayOne(input: List<Int>): Int =
    input.fold(emptyList<Pair<Int, Boolean?>>()) { acc, current -> acc + listOf(current to (acc.lastOrNull()?.let { it.first < current })) }
        .count { it.second == true }
