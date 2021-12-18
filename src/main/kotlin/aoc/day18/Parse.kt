package aoc.day18

import java.util.*

internal fun parse(input: String): SnailNum {
    val stack = Stack<SnailNum>()

    for (char in input) {
        when (char) {
            '[' -> {
                stack.push(EmptySnailPair)
            }
            ']' -> {
                val topPair = stack.pop()

                if (stack.isEmpty()) {
                    return topPair as SnailPair
                }

                when (val nextPair = stack.pop()) {
                    is EmptySnailPair -> {
                        stack.push(IncompleteSnailPair(topPair))
                    }
                    is IncompleteSnailPair -> {
                        stack.push(SnailPair(nextPair.l, topPair))
                    }
                    else -> {
                        throw RuntimeException("Unexpected stack top")
                    }
                }
            }
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                when (val pair = stack.pop()) {
                    is EmptySnailPair -> {
                        stack.push(IncompleteSnailPair(SnailReal(char.digitToInt())))
                    }
                    is IncompleteSnailPair -> {
                        stack.push(SnailPair(pair.l, SnailReal(char.digitToInt())))
                    }
                    else -> {
                        throw RuntimeException("Unexpected stack top")
                    }
                }
            }
            ',' -> {}
        }
    }

    throw RuntimeException("No snail pair parsed")
}