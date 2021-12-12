package aoc.day12

import aoc.Day
import aoc.splitLines

class DayTwelve : Day(12) {
    override val exampleSolution = listOf(10L, 36L)
    override fun solvePartOne(input: String): Long = input.splitLines()
        .map { toEdge(it) }
        .let { findAllPaths(it) }
        .count()
        .toLong()

    override fun solvePartTwo(input: String): Long = input.splitLines()
        .map { toEdge(it) }
        .let { findAllPaths2(it) }
        .map { xs -> xs.joinToString(",") { it.name } }
        .also { println(it.joinToString("\n") ) }
        .count()
        .toLong()

}

fun findAllPaths(edges: List<Edge>): List<List<Vertex>> {
    return findAllPaths(edges, listOf(listOf(Vertex("start"))))
}

fun findAllPaths(edges: List<Edge>, currentPaths: List<List<Vertex>>): List<List<Vertex>> {
    if (currentPaths.all { path -> path.last().isEnd() }) {
        return currentPaths
    }

    return findAllPaths(edges, currentPaths
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

fun findAllPaths2(edges: List<Edge>): List<List<Vertex>> {
    return findAllPaths2(edges, listOf(listOf(Vertex("start"))))
}

fun findAllPaths2(edges: List<Edge>, currentPaths: List<List<Vertex>>): List<List<Vertex>> {
    if (currentPaths.all { path -> path.last().isEnd() }) {
        return currentPaths
    }

    return findAllPaths2(edges, currentPaths
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

fun List<Vertex>.containsSmallNodeTwice() = this.filter { it.isSmall() }.groupBy { it }.any { (v, other) -> other.size >= 2 }
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