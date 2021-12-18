package aoc.day17

import aoc.Day
import arrow.core.memoize
import kotlin.math.abs
import kotlin.math.max

class DaySeventeen : Day(17) {

    override val exampleSolution: List<Long> = listOf(45, 112)

    // target area: x=20..30, y=-10..-5

    override fun solvePartOne(input: String): Long = bruteForce(parseInput(input))
        .maxByOrNull { probe -> probe.maxY }?.maxY?.toLong() ?: -1L

    override fun solvePartTwo(input: String): Long = bruteForce(parseInput(input))
        .groupBy { it.initialTrajectory }
        .keys.size.toLong()
}

private fun generateVectors(maxX: Int, minY: Int, maxY: Int) =
    (0..maxX)
        .asSequence()
        .flatMap { x ->
            (minY..maxY).map { y -> Vector(x, y) }
        }

private val bruteForce = ::bruteForceInternal.memoize()

private fun bruteForceInternal(area: Area) =
    generateVectors(area.x.last, area.y.first, max(abs(area.y.first), abs(area.y.last)))
        .map { simulateProbe(Probe.fromTrajectory(it), area) }
        .flatMap { (bool, probe) -> if (bool) listOf(probe) else emptyList() }

private fun simulateProbe(probe: Probe, area: Area): Pair<Boolean, Probe> = when {
    probe in area -> true to probe
    probe.position.y < area.y.minOf { it } -> false to probe
    probe.position.x > area.x.maxOf { it } -> false to probe
    probe.position.x < area.x.minOf { it } && probe.trajectory.x == 0 -> false to probe
    else -> simulateProbe(probe.step(), area)
}

private val regex = "x=([\\-0-9]+)\\.\\.([\\-0-9]+), y=([\\-0-9]+)\\.\\.([\\-0-9]+)".toRegex()

private fun parseInput(input: String): Area {
    val values = regex.find(input)!!.groupValues.drop(1).map { it.toInt() }
    return Area(values[0]..values[1], values[2]..values[3])
}

data class Vector(val x: Int = 0, val y: Int = 0) {
    operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)
    fun decay(): Vector = when {
        x > 0 -> Vector(x - 1, y - 1)
        x < 0 -> Vector(x + 1, y - 1)
        else -> Vector(0, y - 1)
    }
}

data class Area(val x: IntRange, val y: IntRange) {
    operator fun contains(vector: Vector) = vector.x in x && vector.y in y
    operator fun contains(probe: Probe) = probe.position in this
}

data class Probe(
    val initialTrajectory: Vector,
    val trajectory: Vector = initialTrajectory,
    val positions: List<Vector> = listOf(Vector(0, 0))
) {
    val position by lazy { positions.last() }
    val maxY by lazy { positions.maxOf { pos -> pos.y } }

    fun step() = Probe(initialTrajectory, trajectory.decay(), positions + listOf(position + trajectory))

    companion object {
        fun fromTrajectory(vector: Vector) = Probe(vector)
    }
}