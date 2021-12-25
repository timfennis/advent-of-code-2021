package aoc.day25

import aoc.Day
import aoc.splitLines

class DayTwentyFive : Day(25) {
    override fun test() {

    }



    override val exampleSolution: List<Long> = listOf(58L)
    override fun solvePartOne(input: String): Long {
       val (map, w, h) = parse(input)
       return simulate(map, w, h)
    }

    override fun solvePartTwo(input: String): Long {
        TODO("Not yet implemented")
    }
}
typealias Point = Pair<Int, Int>

private tailrec fun simulateDebug(
    state: Map<Point, Char>,
    width: Int,
    height: Int,
    iterations: Long = 0,
    limit: Long = 100
): Map<Point, Char> {
    val u1 = state.mapKeys { (k, c) ->
        val (x, y) = k
        if (c == '>') {
            val xn = (x + 1) % width
            if (!state.containsKey(xn to y)) {
                xn to y
            } else {
                x to y
            }
        } else {
            x to y
        }
    }
    val u2 = u1.mapKeys { (k, c) ->
        val (x, y) = k
        if (c == 'v') {
            val yn = (y + 1) % height
            if (!u1.containsKey(x to yn)) {
                x to yn
            } else {
                x to y
            }
        } else {
            x to y
        }
    }

    return if (iterations == limit) {
        u2
    } else {
        println("RECURSION")
        simulateDebug(u2, width, height, iterations + 1, limit)
    }
}

private tailrec fun simulate(state: Map<Point, Char>, width: Int, height: Int, iterations: Long = 1): Long {
    val a = state.mapKeys { (k, c) ->
        val (x, y) = k
        if (c == '>') {
            val xn = (x + 1) % width
            if (!state.containsKey(xn to y)) {
                xn to y
            } else {
                x to y
            }
        } else {
            x to y
        }
    }

    val b = a.mapKeys { (k, c) ->
        val (x, y) = k
        if (c == 'v') {
            val yn = (y + 1) % height
            if (!a.containsKey(x to yn)) {
                x to yn
            } else {
                x to y
            }
        } else {
            x to y
        }
    }

    return if (state == b) {
        iterations
    } else {
        println("Staring iteration $iterations")
        simulate(b, width, height, iterations + 1)
    }
}

private fun parse(input: String): Triple<Map<Point, Char>, Int, Int> {
    val map = input.splitLines()
        .flatMapIndexed { y, xs -> xs.mapIndexed { x, c -> (x to y) to c } }
        .filterNot { (_, c) -> c == '.' }
        .toMap()

    return Triple(map, input.splitLines().first().length, input.splitLines().size)
}

private fun printMap(map: Map<Point, Char>, w: Int, h: Int) {
    println("---------------------------")
    for (y in 0 until h) {
        for (x in 0 until w) {
            if (map.containsKey(x to y)) {
                print(map[x to y])
            } else {
                print('.')
            }
        }
        println()
    }
}