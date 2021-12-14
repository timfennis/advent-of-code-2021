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

// -----------------------------------------
// Part one

private fun solvePartOne(template: String, rules: Map<String, String>, steps: Int) =
    expandTemplateWithRules(template, rules, steps)
        .groupBy { char -> char }
        .map { (_, list) -> list.size }
        .sortedDescending()
        .let { list -> list.first() - list.last() }
        .toLong()

private fun expandTemplateWithRules(template: String, rules: Map<String, String>, steps: Int): String =
    if (steps == 0) {
        template
    } else {
        template
            .map { it }
            .zipWithNext()
            .map { "${it.first}${it.second}" }
            .fold(template.first().toString()) { res, pair ->
                res + rules[pair] + pair[1]
            }
            .let {
                expandTemplateWithRules(it, rules, steps - 1)
            }
    }

// -----------------------------------------
// Part two

private fun solvePartTwo(template: String, rules: Map<String, String>, steps: Int) =
    updateFrequencyMap(toFrequencyMap(template), rules, steps) // Update the frequencies of all pairs a number of times
        .map { (pair, count) -> pair[0] to count } // Count only the first character of each pair
        .groupBy { it.first } // Group by character
        .mapValues { (_, value) -> value.sumOf { (_, count) -> count } } // Sum the sums of all characters to find the total
        .let { it.updated(template.last(), it[template.last()]!! + 1) } // Add one extra to the last char in template
        .toList()
        .sortedByDescending { (_, count) -> count }
        .let { list -> list.first().second - list.last().second }
        .toLong()

private tailrec fun updateFrequencyMap(
    freq: Map<String, Long>,
    rules: Map<String, String>,
    steps: Int
): Map<String, Long> =
    if (steps == 0) {
        freq
    } else {
        val out =
            freq.flatMap { (pair, count) -> listOf(rules[pair]!! + pair[1] to count, pair[0] + rules[pair]!! to count) }
                .groupBy { it.first }
                .mapValues { (_, value) -> value.sumOf { it.second } }

        updateFrequencyMap(out, rules, steps - 1)
    }

// -----------------------------------------
// Helper functions

private fun toFrequencyMap(input: String) = input
    .map { it }
    .zipWithNext()
    .groupBy { "${it.first}${it.second}" }
    .mapValues { (_, list) -> list.size.toLong() }

private fun parseRules(rules: String) = rules
    .splitLines()
    .map { it.split(" -> ") }
    .associate { (a, b) -> a to b }