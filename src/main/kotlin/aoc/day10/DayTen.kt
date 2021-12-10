package aoc.day10

import aoc.Day
import aoc.flip
import aoc.median
import aoc.splitLines
import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import java.lang.RuntimeException
import java.util.*

class DayTen : Day(10) {
    override val exampleSolution = listOf(26397L, 288957L)

    override fun solvePartOne(input: String) = solvePartOne(checkLines(input.splitLines())).toLong()

    override fun solvePartTwo(input: String) = solvePartTwo(checkLines(input.splitLines()))
}

// --------------------------------------------------------------------------------
//  Puzzle solver
// --------------------------------------------------------------------------------

fun solvePartTwo(result: List<Either<ParseException, Stack<Char>>>) = result
    .filterIsInstance<Either.Right<Stack<Char>>>()
    .map { calculatePartTwoScore(it.value) }
    .median()

private fun calculatePartTwoScore(stack: Stack<Char>) = stack
    .reversed()
    .fold(0L) { acc, char -> (acc * 5) + getPartTwoCharScore(getMatchingBracket(char)) }

fun solvePartOne(result: List<Either<ParseException, Stack<Char>>>) = result
    .filterIsInstance<Either.Left<ParseException.IllegalCharacterFound>>()
    .sumOf { getPartOneScore(it.value.char) }

private fun getPartOneScore(char: Char) = when (char) {
    ')' -> 3
    ']' -> 57
    '}' -> 1197
    '>' -> 25137
    else -> throw RuntimeException("Invalid char $char, no matching score")
}

private fun getPartTwoCharScore(char: Char) = when (char) {
    ')' -> 1
    ']' -> 2
    '}' -> 3
    '>' -> 4
    else -> throw RuntimeException("Invalid char $char, no matching score")
}

// --------------------------------------------------------------------------------
//  Parser
// --------------------------------------------------------------------------------

private val brackets = mapOf(
    '{' to '}',
    '[' to ']',
    '(' to ')',
    '<' to '>',
)

private fun checkLines(lines: List<String>) = lines.map { line -> checkLine(line) }

private fun checkLine(line: String) =
    line.foldIndexed<Either<ParseException, Stack<Char>>>(Stack<Char>().right()) { index, eitherStack, char ->
        eitherStack.flatMap { stack ->
            if (isOpenChar(char)) {
                stack.push(char)
                stack.right()
            } else if (closeCharMatchesOpen(char, stack.peek())) {
                stack.pop()
                stack.right()
            } else {
                ParseException.IllegalCharacterFound(char, index).left()
            }
        }
    }

private fun isOpenChar(char: Char) = brackets.keys.contains(char)

private fun closeCharMatchesOpen(open: Char, close: Char) = open == getMatchingBracket(close)

private fun getMatchingBracket(char: Char) =
    brackets[char] ?: brackets.flip()[char] ?: throw RuntimeException("Invalid character $char")

sealed class ParseException(private val message: String) {
    override fun toString() = message
    class IllegalCharacterFound(val char: Char, index: Int) : ParseException("Illegal character found at $index: $char")
}
