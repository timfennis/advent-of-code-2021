package aoc.day10

import aoc.flip
import aoc.median
import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import java.lang.RuntimeException
import java.util.*

fun main() {
    val result = checkLines(dayTenInput)

    println("Part one: " + solvePartOne(result))
    println("Part two: " + solvePartTwo(result))
}

// --------------------------------------------------------------------------------
//  Puzzle solver
// --------------------------------------------------------------------------------

private fun solvePartTwo(result: List<Either<ParseException, Stack<Char>>>) = result
    .filterIsInstance<Either.Right<Stack<Char>>>()
    .map { calculatePartTwoScore(it.value) }
    .median()

fun calculatePartTwoScore(stack: Stack<Char>) = stack
    .reversed()
    .fold(0L) { acc, char -> (acc * 5) + getPartTwoCharScore(getMatchingBracket(char)) }

fun solvePartOne(result: List<Either<ParseException, Stack<Char>>>) = result
    .filterIsInstance<Either.Left<ParseException.IllegalCharacterFound>>()
    .sumOf { getPartOneScore(it.value.char) }

fun getPartOneScore(char: Char) = when (char) {
    ')' -> 3
    ']' -> 57
    '}' -> 1197
    '>' -> 25137
    else -> throw RuntimeException("Invalid char $char, no matching score")
}

fun getPartTwoCharScore(char: Char) = when (char) {
    ')' -> 1
    ']' -> 2
    '}' -> 3
    '>' -> 4
    else -> throw RuntimeException("Invalid char $char, no matching score")
}

// --------------------------------------------------------------------------------
//  Parser
// --------------------------------------------------------------------------------

fun checkLines(lines: List<String>) = lines.map { line -> checkLine(line) }

fun checkLine(line: String) =
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

val brackets = mapOf(
    '{' to '}',
    '[' to ']',
    '(' to ')',
    '<' to '>',
)

fun isOpenChar(char: Char) = brackets.keys.contains(char)

fun closeCharMatchesOpen(open: Char, close: Char) = open == getMatchingBracket(close)

fun getMatchingBracket(char: Char) =
    brackets[char] ?: brackets.flip()[char] ?: throw RuntimeException("Invalid character $char")

sealed class ParseException(private val message: String) {
    override fun toString() = message
    class IllegalCharacterFound(val char: Char, index: Int) : ParseException("Illegal character found at $index: $char")
}
