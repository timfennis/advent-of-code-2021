package aoc.day5

/**
 * Ongecontrolleerde celldeling
 */
fun main ()
{
    val x = dayFiveInput
        .flatMap { it.points() }
        .groupBy { it }
        .map { it.value.size }
        .count { it >= 2 }

    println(x)
}