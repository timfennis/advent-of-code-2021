package aoc.day19

import arrow.core.memoize
import kotlin.math.abs

val allRotations = ::allRotationsList.memoize()

private fun allRotationsList(point: Point3D): List<Point3D> = allRotationsSequence(point).toList()
private fun allRotationsSequence(point: Point3D): Sequence<Point3D> = sequence {
    var v = point
    for (cycle in 1..2) {
        for (step in 1..3) {
            v = v.roll()
            yield(v)

            for (i in 1..3) {
                v = v.turn()
                yield(v)
            }
        }
        v = v.roll().turn().roll()
    }
}


data class Point3D(val x: Int, val y: Int, val z: Int) {
    infix fun distanceTo(other: Point3D) = listOf(
        ((this.x - other.x) * (this.x - other.x)),
        ((this.y - other.y) * (this.y - other.y)),
        ((this.z - other.z) * (this.z - other.z)),
    ).sum()

    @Suppress("SpellCheckingInspection")
    infix fun manhattenDistanceTo(other: Point3D) = abs(this.x - other.x) + abs(this.y - other.y) + abs(this.z - other.z)

    operator fun minus(other: Point3D) = Point3D(this.x - other.x, this.y - other.y, this.z - other.z)
    operator fun plus(other: Point3D) = Point3D(this.x + other.x, this.y + other.y, this.z + other.z)
    operator fun times(other: Point3D) = Point3D(this.x * other.x, this.y * other.y, this.z * other.z)

    fun roll() = Point3D(x, z, -y)
    fun turn() = Point3D(-y, x, z)
}
