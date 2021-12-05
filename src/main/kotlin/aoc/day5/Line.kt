package aoc.day5

data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
    companion object {
        fun fromList(list: List<Int>) = Line(list[0], list[1], list[2], list[3])
    }

    fun points() = when {
        x1 == x2 -> (y1 countTil y2).map { Point(x1, it) }
        y1 == y2 -> (x1 countTil x2).map { Point(it, y1) }
        else -> {
            (x1 countTil x2).zip(y1 countTil y2).map { (x,y) -> Point(x, y) }
        }
    }

    fun isDiagonal() = x1 != x2 && y1 != y2
}