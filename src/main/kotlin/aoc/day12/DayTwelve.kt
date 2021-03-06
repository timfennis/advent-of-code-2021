package aoc.day12

import aoc.Day
import aoc.splitLines

class DayTwelve : Day(12) {
    override val exampleSolution = listOf(10L, 36L)
    override fun solvePartOne(input: String): Long = input.splitLines()
        .map { toEdge(it) }
        .let { findAllPathsPartOne(it) }
        .count()
        .toLong()

    override fun solvePartTwo(input: String): Long = input.splitLines()
        .map { toEdge(it) }
        .let { findPathsPartTwo(it) }
        .count()
        .toLong()
}

fun findAllPathsPartOne(edges: List<Edge>): List<List<Vertex>> {
    return findAllPathsPartOne(edges, listOf(listOf(Vertex("start"))))
}

fun findAllPathsPartOne(edges: List<Edge>, currentPaths: List<List<Vertex>>): List<List<Vertex>> {
    if (currentPaths.all { path -> path.last().isEnd() }) {
        return currentPaths
    }

    return findAllPathsPartOne(edges, currentPaths
        .flatMap { currentPath ->
            if (currentPath.last().isEnd()) {
                listOf(currentPath)
            } else {
                edges
                    .findAllConnectedVertices(currentPath.last())
                    .filterNot { it.isStart() } // don't go back to the start
                    .filterNot { potentialConnectedVertex -> potentialConnectedVertex.isSmall() && currentPath.any { it == potentialConnectedVertex } }
                    .map { c -> currentPath + listOf(c) }
            }
        })
}

fun findPathsPartTwo(edges: List<Edge>): List<List<Vertex>> {
    return findPathsPartTwo(edges, listOf(listOf(Vertex("start"))))
}

fun findPathsPartTwo(edges: List<Edge>, currentPaths: List<List<Vertex>>): List<List<Vertex>> {
    if (currentPaths.all { path -> path.last().isEnd() }) {
        return currentPaths
    }

    return findPathsPartTwo(edges, currentPaths
        .flatMap { currentPath ->
            if (currentPath.last().isEnd()) {
                listOf(currentPath)
            } else {
                edges
                    .findAllConnectedVertices(currentPath.last())
                    .filterNot { it.isStart() } // don't go back to the start
                    .filterNot { potentialConnectedVertex -> potentialConnectedVertex.isSmall() && currentPath.count { it == potentialConnectedVertex } >= currentPath.allowedDoubles() }
                    .map { c -> currentPath + listOf(c) }
            }
        })
}

fun List<Vertex>.containsSmallNodeTwice() =
    this.filter { it.isSmall() }.groupBy { it }.any { (_, other) -> other.size >= 2 }

fun List<Vertex>.allowedDoubles() = if (this.containsSmallNodeTwice()) 1 else 2

fun toEdge(input: String) = Edge(Vertex(input.split('-').first()), Vertex(input.split('-').last()))

data class Edge(val first: Vertex, val second: Vertex)

data class Vertex(val name: String) {
    fun isLarge() = name.all { it.isUpperCase() }
    fun isSmall() = !isLarge() && !isStart() && !isEnd()
    fun isEnd() = name == "end"
    fun isStart() = name == "start"
}


fun List<Edge>.findAllConnectedVertices(from: Vertex) =
    this.mapNotNull { if (it.first == from) it.second else if (it.second == from) it.first else null }