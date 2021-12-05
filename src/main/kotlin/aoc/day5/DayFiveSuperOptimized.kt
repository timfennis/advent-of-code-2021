package aoc.day5

/**
 * Ongecontrolleerde celldeling
 */
fun main ()
{
    // The only other code this uses from the package is Line.kt (and Point.kt which might as well have been a Pair)
    val x = dayFiveInput
        .flatMap { it.points() }
        .groupBy { it }
        .map { it.value.size }
        .count { it >= 2 }

    println(x)
}