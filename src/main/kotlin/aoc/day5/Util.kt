package aoc.day5

infix fun Int.countTil(y: Int) =  when {
    this < y -> (this .. y)
    this > y -> (this downTo  y)
    else -> listOf(this)
}

/**
 * Convert an int to a number for rendering
 */
fun Int.toRenderChar() =  when(this) {
    0 -> '.'
    1,2,3,4,5,6,7,8,9 -> this.digitToChar()
    else -> 'X'
}