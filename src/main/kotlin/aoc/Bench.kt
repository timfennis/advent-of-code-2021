package aoc

import java.util.ArrayList


/**
 * Iterates provided by [callback] code [iterations]x[testCount] times.
 * Performs warming by iterating [iterations]x[warmCount] times.
 */
fun simpleMeasureTest(
    iterations: Int = 1000,
    testCount: Int = 10,
    warmCount: Int = 2,
    prefix: String = "Test",
    callback: ()->Unit
) {
    val results = ArrayList<Long>()
    var totalTime = 0L
    var t = 0

    val printPrefix = "[$prefix]"
    println("$printPrefix -> go")

    while (++t <= testCount + warmCount) {
        val startTime = System.currentTimeMillis()

        var i = 0
        while (i++ < iterations)
            callback()

        if (t <= warmCount) {
            println("$printPrefix Warming $t of $warmCount")
            continue
        }

        val time = System.currentTimeMillis() - startTime
        println(printPrefix+" "+time.toString()+"ms")

        results.add(time)
        totalTime += time
    }

    results.sort()

    val average = totalTime / testCount
    val median = results[results.size / 2]

    println("$printPrefix -> average=${average}ms / median=${median}ms")
}