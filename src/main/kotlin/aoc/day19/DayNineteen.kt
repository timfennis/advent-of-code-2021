package aoc.day19

import aoc.*
import arrow.core.memoize

class DayNineteen : Day(19) {

    override val exampleSolution = listOf(79L, 3621L)

    override fun test() {
        val distance = Point3D(1105, -1205, 1229).manhattenDistanceTo(Point3D(-92,-2380,-20))
        check(distance == 3621)
    }

    override fun solvePartOne(input: String) = input
        .split("\n\n")
        .map { Scanner.fromStringList(it.splitLines().filterNot { line -> line.startsWith("---") }) }
        .let { scanners ->
            val (beacons, _) = locateScanners(scanners[0].beacons, scanners.drop(1))
            beacons
        }.size.toLong()


    override fun solvePartTwo(input: String) = input
        .split("\n\n")
        .map { Scanner.fromStringList(it.splitLines().filterNot { line -> line.startsWith("---") }) }
        .let { scanners ->
            val (_, foundScanners) = locateScanners(scanners[0].beacons, scanners.drop(1))
            foundScanners.combinations(foundScanners).maxOf { (a, b) -> a.location.manhattenDistanceTo(b.location) }.toLong()
        }
}

tailrec fun locateScanners(initial: Set<Point3D>, remainingScanners: List<Scanner>, foundScanners: Set<FoundScanner> = emptySet()): Pair<Set<Point3D>, Set<FoundScanner>> {

    if (remainingScanners.isEmpty()) {
        return initial to foundScanners
    }

    val (allBeacons, newFoundScanners) = remainingScanners.fold(Pair(initial, emptySet<FoundScanner>())) { (set, foundScanners), scanner ->
        val (offset, newBeacons) = translateToCoordinateSystemOrNullWithRotations(set, scanner.beacons) ?: (null to null)

        if (newBeacons != null && offset != null) {
            set.union(newBeacons) to foundScanners.union(setOf(FoundScanner(scanner, offset)))
        } else {
            set to foundScanners
        }
    }

    return locateScanners(allBeacons, remainingScanners.filterNot { remainingScanner -> foundScanners.any { foundScanner -> foundScanner.scanner == remainingScanner } }, foundScanners.union(newFoundScanners))
}

fun translateToCoordinateSystemOrNullWithRotations(
    beaconsA: Set<Point3D>,
    beaconsB: Set<Point3D>
): Pair<Point3D, List<Point3D>>? {
    val variationsByBeacon = findVariationsByBeacon(beaconsB)

    return variationsByBeacon.firstNotNullOfOrNull { translateToCoordinateSystemOrNull(beaconsA, it) }
}

val findVariationsByBeacon = ::findVariationsByBeaconInternal.memoize()

private fun findVariationsByBeaconInternal(beaconsB: Set<Point3D>): List<Set<Point3D>> {
    val variationsByPoint = beaconsB.map { allRotations(it) }
    return List(variationsByPoint.first().size) { index -> List(variationsByPoint.size) { beaconIndex -> variationsByPoint[beaconIndex][index] }.toSet() }
}

private fun translateToCoordinateSystemOrNull(
    targetCoordinates: Set<Point3D>,
    sourceCoordinates: Set<Point3D>
): Pair<Point3D, List<Point3D>>? {
    val map = targetCoordinates.flatMapIndexed { indexA, beaconA ->
        sourceCoordinates.mapIndexed { indexB, beaconB ->
            Triple(indexA, indexB, beaconA - beaconB) to sourceCoordinates.map { b -> b - beaconB + beaconA }
        }
    }

    return map.firstOrNull { it.second.intersect(targetCoordinates).size >= 12 }
        ?.mapFirst { (_, _, point) -> point }
}

data class Scanner(val beacons: Set<Point3D>) {
    companion object {
        fun fromStringList(input: List<String>) =
            Scanner(input.map { line ->
                val (x, y, z) = line.split(',').map { num -> num.toInt() }
                Point3D(x, y, z)
            }.toSet())
    }
}
data class FoundScanner(val scanner: Scanner, val location: Point3D)