@file:Suppress("FunctionName")

package aoc.day8

import aoc.flip
import java.lang.RuntimeException

fun main() {
    println("Part 1 example: " + solvePartOne(dayEightExampleInput))
    println("Part 1: " + solvePartOne(dayEightInput))

    println("Part 2 example: " + solvePartTwo(dayEightExampleInput))
    println("Part 2: " + solvePartTwo(dayEightInput))
}

private fun solvePartOne(input: List<Note>) = listOf(
    input.sumOf { it.outputValues.count { v -> v.length == 2 } },
    input.sumOf { it.outputValues.count { v -> v.length == 4 } },
    input.sumOf { it.outputValues.count { v -> v.length == 3 } },
    input.sumOf { it.outputValues.count { v -> v.length == 7 } },
).sum()

private fun solvePartTwo(input: List<Note>) = input.sumOf { solveNote(it) }

/**
 * I'm going to break my own rules for this one
 */
private fun solveNote(note: Note): Int {
    val cf = find_cf(note)
    val bd = find_bd(note, cf)

    val a = find_segment_with_all_other_of_length(note, cf, 3)
    val g = find_segment_with_all_other_of_length(note, cf + a + bd, 6)
    val e = find_e(note, cf + a + bd + g)
    val d = find_segment_with_all_other_of_length(note, cf + g + a, 5)
    val b = find_segment_with_all_other_of_length(note, cf + d, 4)
    val c = find_segment_with_all_other_of_length(note, "$a$d$e$g", 5)
    val f = find_segment_with_all_other_of_length(note, "$c", 2)

    val mapping = mapOf(
        'a' to a,
        'b' to b,
        'c' to c,
        'd' to d,
        'e' to e,
        'f' to f,
        'g' to g,
    )

    return note.outputValues
        .map { fixSegments(it, mapping.flip()) }
        .map { it.toDecimal() }
        .joinToString("")
        .toInt()
}



fun fixSegments(input: String, mapping: Map<Char, Char>): List<Char> = input.map { mapping[it]!! }

private fun find_cf(note: Note) = note.patterns.first { it.length == 2 }
private fun find_bd(note: Note, oneSegments: String) = note.patterns.first { it.length == 4 }.filterNot { it in oneSegments }
private fun find_e(note: Note, other: String) = find_segment_with_all_other_of_length(note, other, 7)

private fun find_segment_with_all_other_of_length(note: Note, other: String, length: Int) =
    note.patterns.first { it.length == length && other.all { c -> it.contains(c) } }.filterNot { it in other }.first()

fun List<Char>.toDecimal(): Int = when(this.sorted().joinToString("")) {
    "abcefg" -> 0
    "cf" -> 1
    "acdeg" -> 2
    "acdfg" -> 3
    "bcdf" -> 4
    "abdfg" -> 5
    "abdefg" -> 6
    "acf" -> 7
    "abcdefg" -> 8
    "abcdfg" -> 9
    else -> throw RuntimeException("Unexpected input: " + this.sorted().joinToString(""))

}