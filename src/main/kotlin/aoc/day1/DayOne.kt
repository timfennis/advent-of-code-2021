package aoc.day1

fun main() {
    println(solveDayOnePartOne(dayOneInput))
    println(solveDayOnePartOne(solveDayOnePartTwo(dayOneInput)))
}

fun solveDayOnePartTwo(input: List<Int>) = input.zipWithNext().zipWithNext { a, b -> a.first + a.second + b.second }

fun solveDayOnePartOne(input: List<Int>) = input.zipWithNext().count { it.first < it.second }
