package aoc

import java.util.ArrayList


/**
 * Iterates provided by [callback] code [ITERATIONS]x[TEST_COUNT] times.
 * Performs warming by iterating [ITERATIONS]x[WARM_COUNT] times.
 */
fun simpleMeasureTest(
    ITERATIONS: Int = 1000,
    TEST_COUNT: Int = 10,
    WARM_COUNT: Int = 2,
    callback: ()->Unit
) {
    val results = ArrayList<Long>()
    var totalTime = 0L
    var t = 0

    println("$PRINT_REFIX -> go")

    while (++t <= TEST_COUNT + WARM_COUNT) {
        val startTime = System.currentTimeMillis()

        var i = 0
        while (i++ < ITERATIONS)
            callback()

        if (t <= WARM_COUNT) {
            println("$PRINT_REFIX Warming $t of $WARM_COUNT")
            continue
        }

        val time = System.currentTimeMillis() - startTime
        println(PRINT_REFIX+" "+time.toString()+"ms")

        results.add(time)
        totalTime += time
    }

    results.sort()

    val average = totalTime / TEST_COUNT
    val median = results[results.size / 2]

    println("$PRINT_REFIX -> average=${average}ms / median=${median}ms")
}

/**
 * Used to filter console messages easily
 */
private val PRINT_REFIX = "[TimeTest]"