package aoc.day1

fun main() {
    println(solveDayOnePartOne(dayOneInput))
    println(solveDayOnePartOne(solveDayOnePartTwo(dayOneInput)))
}

fun solveDayOnePartTwo(input: List<Int>) = input.zipWithNext().zipWithNext { a, b -> Triple(a.first, a.second, b.second).sum() }

fun solveDayOnePartOne(input: List<Int>) = input.zipWithNext().count { it.first < it.second }

fun Triple<Int, Int, Int>.sum() = this.first + this.second + this.third