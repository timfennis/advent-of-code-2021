package aoc.day22

import aoc.*
import java.util.*
import kotlin.math.ceil

class DayTwentyTwo : Day(22) {

    override val exampleSolution = listOf(474140L, 2758514936282235L)

    override fun solvePartOne(input: String) = solve(parse(input).filter { it.inRange() })

    override fun solvePartTwo(input: String) = solve(parse(input))
}


private fun solve(instructions: List<Instruction>): Long {
    val xs = instructions.xs().map { it.toLong() }
    val ys = instructions.ys().map { it.toLong() }
    val zs = instructions.zs().map { it.toLong() }

    val zlen = ceil(zs.size/8.0).toInt()

    val cubes = Array(xs.size) {
        Array(ys.size) {
            BitSet(zlen)
        }
    }

    instructions.forEach { inst ->
        val x1 = xs.indexOf(inst.x.first.toLong())
        val x2 = xs.indexOf(inst.x.last.toLong() + 1)
        val y1 = ys.indexOf(inst.y.first.toLong())
        val y2 = ys.indexOf(inst.y.last.toLong() + 1)
        val z1 = zs.indexOf(inst.z.first.toLong())
        val z2 = zs.indexOf(inst.z.last.toLong() + 1)
        // We loop over al the intervals we found above but use until because the last+1 interval is the bound
        (x1 until x2).forEach { x ->
            (y1 until y2).forEach { y ->
                (z1 until z2).forEach { z ->
                    cubes[x][y][z] = inst.operation
                }
            }
        }
    }

    var sum = 0L

    for ((x1, x2) in xs.indices.zipWithNext()) {
        for ((y1, y2) in ys.indices.zipWithNext()) {
            for ((z1, z2) in zs.indices.zipWithNext()) {
                if (cubes[x1][y1][z1]) {
                    val xr = xs[x2] - xs[x1]
                    val yr = ys[y2] - ys[y1]
                    val zr = zs[z2] - zs[z1]

                    sum += (xr * yr * zr)
                }
            }
        }
    }

    return sum
}

private val regex = "(on|off) x=(-?\\d+)\\.\\.(-?\\d+),y=(-?\\d+)\\.\\.(-?\\d+),z=(-?\\d+)\\.\\.(-?\\d+)".toRegex()
private fun parse(input: String) = input.splitLines()
    .map { line -> regex.find(line)?.groupValues!! }
    .map { m ->
        Instruction(
            m[1] == "on",
            m[2].toInt()..m[3].toInt(),
            m[4].toInt()..m[5].toInt(),
            m[6].toInt()..m[7].toInt()
        )
    }

data class Instruction(
    val operation: Boolean,
    val x: IntRange,
    val y: IntRange,
    val z: IntRange,
)

private fun List<Instruction>.xs() =
    this.flatMap { listOf(it.x.first, it.x.last + 1) }.toSortedSet()

private fun List<Instruction>.ys() =
    this.flatMap { listOf(it.y.first, it.y.last + 1) }.toSortedSet()

private fun List<Instruction>.zs() =
    this.flatMap { listOf(it.z.first, it.z.last + 1) }.toSortedSet()

private fun Triple<Int, Int, Int>.inRange() = this.toList().all { it in -50..50 }

private fun Instruction.pointMap() =
    this.x.flatMap { x -> this.y.flatMap { y -> this.z.map { z -> Triple(x, y, z) to operation } } }.toMap()

private fun Instruction.pointNodes() =
    this.x.flatMap { x -> this.y.flatMap { y -> this.z.map { z -> Triple(x, y, z) to operation } } }

private fun Instruction.inRange() = listOf(
    this.x.first,
    this.x.last,
    this.y.first,
    this.y.last,
    this.z.first,
    this.z.last
).all { it in -50..50 }