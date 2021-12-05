package aoc.day5

/**
 * If only I had realised sooner that this is possible
 */
fun main() {
    // Mutable
    val points = dayFiveInput.fold(mutableMapOf<Point,Int>()) { acc, line ->
        line.points().forEach { point ->
            acc.compute(point) { _, b -> if (b == null) 1 else b + 1 }
        }
        acc
    }.count { it.value >= 2 }

    println(points)

    // Immutable (and slow as fuck)
    val points2 = dayFiveInput
        .fold(listOf<Map<Point,Int>>()) { acc, line -> acc + line.points().associateWith { 1 } }
        .reduce { acc, map -> acc.mergeWith(map) { a, b -> (a ?: 0) + (b ?: 0) } }
        .count { it.value >= 2 }

    println(points2)


}

fun <A,B> Map<A,B>.mergeWith(other: Map<A,B>, merger: (B?, B?) -> B): Map<A,B> = (this.keys + other.keys).associateWith { key -> merger(this[key], other[key]) }