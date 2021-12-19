package aoc.day18

import aoc.Day
import aoc.splitLines
import kotlin.math.ceil
import kotlin.math.floor

class DayEighteenRegex : Day(18) {

    override val exampleSolution: List<Long> = listOf(4140, 3993)

    override fun solvePartOne(input: String) = input.splitLines()
        .map { snailReduce(it) }
        .reduce { acc, s -> snailAdd(acc, s) }
        .let { parse(it).magnitude().toLong() }

    override fun solvePartTwo(input: String) = combinations(input.splitLines())
        .map { (a, b) -> parse(snailAdd(a, b)).magnitude() }
        .maxOf { it.toLong() }
}


val regex = "\\[\\d+,\\d+]".toRegex()

private fun snailAdd(a: String, b: String) = snailReduce("[$a,$b]")
private fun snailReduce(num: String): String {
    val explode = findExplodePair(num)

    if (explode != null) {
        return snailReduce(num.explodeInRange(explode))
    }

    val split = findSplitNum(num)

    if (split != null) {
        return snailReduce(num.splitInRange(split))
    }

    return num
}

private fun findExplodePair(num: String): IntRange? {
    val allPairs = regex.findAll(num)

    for (pair in allPairs) {

        val open = num.take(pair.range.first + 1).count { it == '[' }
        val close = num.take(pair.range.first + 1).count { it == ']' }

        val diff = open - close

        if (diff >= 5) {
            return pair.range
        }
    }

    return null
}

private fun findSplitNum(num: String): IntRange? {
    val allNumbers = regexNumber.findAll(num)
    for (match in allNumbers) {
        val number = num.substring(match.range).toInt()

        if (number > 9) {
            return match.range
        }
    }

    return null
}

val regexNumber = "\\d+".toRegex()

private fun String.explodeInRange(range: IntRange): String {

    val pair = this.substring(range).snailNumberToPair()

    val stringBefore = this.substring(0 until range.first)
    val stringAfter = this.drop(range.last + 1)

    val numbersBefore = regexNumber.findAll(stringBefore).lastOrNull()
    val numbersAfter = regexNumber.findAll(stringAfter).firstOrNull()

    val newBefore = if (numbersBefore != null) {
        stringBefore.replaceRange(numbersBefore.range, (numbersBefore.value.toInt() + pair.first).toString())
    } else {
        stringBefore
    }

    val newAfter = if (numbersAfter != null) {
        stringAfter.replaceRange(numbersAfter.range, (numbersAfter.value.toInt() + pair.second).toString())
    } else {
        stringAfter
    }

    return "${newBefore}0${newAfter}"
}

private fun String.splitInRange(range: IntRange): String {

    val num = this.substring(range).toInt()

    val a = floor(num / 2.0).toInt()
    val b = ceil(num / 2.0).toInt()

    return this.replaceRange(range, "[$a,$b]")
}

private fun String.snailNumberToPair() =
    this.drop(1).dropLast(1).split(',').map { it.toInt() }.let { it.first() to it.last() }


private fun <A> combinations(
    list: Iterable<A>
): Sequence<Pair<A, A>> =
    sequence {
        list.forEach { a ->
            list.forEach { b ->
                if (a != b) {
                    yield(a to b)
                    yield(b to a)
                }
            }
        }
    }
