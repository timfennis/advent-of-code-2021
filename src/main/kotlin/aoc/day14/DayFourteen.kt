@file:Suppress("SameParameterValue")

package aoc.day14

import aoc.Day
import aoc.splitLines
import aoc.updated

class DayFourteen : Day(14) {
    override val exampleSolution = listOf(1588L, 2188189693529L)

    override fun solvePartOne(input: String) = input.split("\n\n")
        .let { (template, rules) -> template to parseRules(rules) }
        .let { (template, rules) -> solvePartOne(template, rules, 10) }

    override fun solvePartTwo(input: String) = input.split("\n\n")
        .let { (template, rules) -> template to parseRules(rules) }
        .let { (template, rules) -> solvePartTwo(template, rules, 40) }

}

typealias Rules = Map<String, String>
typealias FreqMap = Map<String, Long>

// -----------------------------------------
// Part one

private fun solvePartOne(template: String, rules: Rules, steps: Int) =
    expandTemplateWithRules(template, rules, steps)
        .groupBy { char -> char }
        .map { (_, list) -> list.size }
        .sortedDescending()
        .let { list -> list.first() - list.last() }
        .toLong()

private tailrec fun expandTemplateWithRules(template: String, rules: Rules, steps: Int): String = when (steps) {
    0 -> template
    else ->
        expandTemplateWithRules(
            template = template.toPairs()
                .fold(template.first().toString()) { res, pair ->
                    res + rules[pair] + pair[1]
                },
            rules,
            steps - 1
        )
}

private fun String.toPairs() = this.zipWithNext { a, b -> "$a$b" }

// -----------------------------------------
// Part two

private fun solvePartTwo(template: String, rules: Rules, steps: Int) =
    updateFrequencyMap(toFrequencyMap(template), rules, steps) // Update the frequencies of all pairs a number of times
        .map { (pair, count) -> pair[0] to count } // Count only the first character of each pair
        .groupBy { it.first } // Group by character
        .mapValues { (_, value) -> value.sumOf { (_, count) -> count } } // Sum the sums of all characters to find the total
        .let { it.updated(template.last(), it[template.last()]!! + 1) } // Add one extra to the last char in template
        .toList()
        .sortedByDescending { (_, count) -> count }
        .let { list -> list.first().second - list.last().second }
        .toLong()

private tailrec fun updateFrequencyMap(freq: FreqMap, rules: Rules, steps: Int): FreqMap = when (steps) {
    0 -> freq
    else -> {
        updateFrequencyMap(
            freq = freq
                .flatMap { (pair, count) -> listOf(rules[pair]!! + pair[1] to count, pair[0] + rules[pair]!! to count) }
                .groupBy { it.first }
                .mapValues { (_, value) -> value.sumOf { it.second } },
            rules,
            steps - 1
        )
    }
}
// -----------------------------------------
// Helper functions

private fun toFrequencyMap(input: String): FreqMap = input
    .zipWithNext { a, b -> "$a$b"}
    .groupBy { it }
    .mapValues { (_, list) -> list.size.toLong() }

private fun parseRules(rulesString: String) = rulesString
    .splitLines()
    .map { it.split(" -> ") }
    .associate { (a, b) -> a to b }