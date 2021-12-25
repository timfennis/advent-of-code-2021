package aoc

import aoc.day10.DayTen
import aoc.day11.DayEleven
import aoc.day12.DayTwelve
import aoc.day13.DayThirteen
import aoc.day14.DayFourteen
import aoc.day15.DayFifteen
import aoc.day16.DaySixteen
import aoc.day17.DaySeventeen
import aoc.day18.DayEighteenRegex
import aoc.day19.DayNineteen
import aoc.day20.DayTwenty
import aoc.day22.DayTwentyTwo
import aoc.day23.DayTwentyThree
import aoc.day24.DayTwentyFour
import aoc.day25.DayTwentyFive
import java.io.File
import java.lang.management.MemoryType

import java.lang.management.MemoryPoolMXBean

import java.lang.management.ManagementFactory


private val days = listOf(
    DayTen(),
    DayEleven(),
    DayTwelve(),
    DayThirteen(),
    DayFourteen(),
    DayFifteen(),
    DaySixteen(),
    DaySeventeen(),
    DayEighteenRegex(),
    // DayEighteenTheBrokenVersion(),
    DayNineteen(),
    DayTwenty(),
    DayTwentyTwo(),
    DayTwentyThree(),
    DayTwentyFive(),
    DayTwentyFour(),
)


fun main() {
    runDay(days.last())
}

fun runDay(day: Day) {
    println("Day[${day.number}] tests")
    day.test()

    val ex = File("input/day${day.number}_example")
    if (ex.exists()) {
        if (runExamplePart(day, 1, day.solvePartOne(ex.readText()))) return
    }

    val inFile = File("input/day${day.number}")
    val input = if (inFile.exists()) inFile.readText() else ""


    println("Day[${day.number}] part 1: ${day.solvePartOne(input)}")

    if (ex.exists()) {
        if (runExamplePart(day, 2, day.solvePartTwo(ex.readText()))) return
    }

    timed {
        println("Day[${day.number}] part 2: ${day.solvePartTwo(input)}")
    }
}

private fun runExamplePart(day: Day, part: Int, solution: Long): Boolean {
    print("Day[${day.number}] example part $part: $solution")
    if (day.exampleSolution[part - 1] != solution) {
        println(" incorrect ${day.exampleSolution[part - 1]} expected")
        return true
    } else {
        println(" correct")
    }

    return false
}

abstract class Day(val number: Int) {
    open val exampleSolution = listOf(-1L, -1L)

    open fun test() {
        println("No tests defined for day $number")
    }

    abstract fun solvePartOne(input: String): Long
    abstract fun solvePartTwo(input: String): Long

}

private fun timed(f: () -> Unit) {
    val start = System.nanoTime()
    f()
    val end = System.nanoTime()
    if (end - start <= 1000) {
        println("Ran in ${end - start} nanoseconds")
    }

    if (end - start <= 1000000) {
        println("Ran in ${(end - start) / 1000.0} microseconds")
    }

    if (end - start <= 1000000000) {
        println("Ran in ${(end - start) / 1000000.0} miliseconds")

    }

    println("Ran in ${(end - start) / 1000000000.0} seconds")

    val pools: List<MemoryPoolMXBean> = ManagementFactory.getMemoryPoolMXBeans()
    var total: Long = 0
    for (memoryPoolMXBean: MemoryPoolMXBean in pools) {
        if (memoryPoolMXBean.type == MemoryType.HEAP) {
            val peakUsed: Long = memoryPoolMXBean.peakUsage.used
            println("Peak used for: ${memoryPoolMXBean.name} is: ${humanReadableByteCountBin(peakUsed)}")
            total += peakUsed
        }
    }

    println("Total heap peak used: ${humanReadableByteCountBin(total)}")

}

fun humanReadableByteCountBin(bytes: Long) = when {
    bytes == Long.MIN_VALUE || bytes < 0 -> "N/A"
    bytes < 1024L -> "$bytes B"
    bytes <= 0xfffccccccccccccL shr 40 -> "%.1f KiB".format(bytes.toDouble() / (0x1 shl 10))
    bytes <= 0xfffccccccccccccL shr 30 -> "%.1f MiB".format(bytes.toDouble() / (0x1 shl 20))
    bytes <= 0xfffccccccccccccL shr 20 -> "%.1f GiB".format(bytes.toDouble() / (0x1 shl 30))
    bytes <= 0xfffccccccccccccL shr 10 -> "%.1f TiB".format(bytes.toDouble() / (0x1 shl 40))
    bytes <= 0xfffccccccccccccL -> "%.1f PiB".format((bytes shr 10).toDouble() / (0x1 shl 40))
    else -> "%.1f EiB".format((bytes shr 20).toDouble() / (0x1 shl 40))
}