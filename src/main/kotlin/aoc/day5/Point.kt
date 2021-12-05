package aoc.day5

data class Point(val x: Int, val y: Int) {
    fun toOffset(width: Int): Int {
        // println("$x, $y (in $width) = ${(y * width) + x}")
        return (y * width) + x
    }
}