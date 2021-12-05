@file:Suppress("unused")

package aoc.day5

/**
 * Immutable Grid implementation that's horribly slow
 */
class Grid(private val width: Int, private val height: Int, private val data: List<Int> = emptyArrayList(width, height)) {

    private fun List<Point>.toGridData(width: Int, height: Int) =
        this.fold(initial = emptyArrayList(width, height)) { acc, point ->
            acc[point.toOffset(width)] = 1
            acc
        }

    fun withLine(line: Line) = Grid(
        data = line.points()
            .toGridData(width, height)
            .zip(data)
            .map { (n, o) -> o + n },
        width = width,
        height = height
    )

    override fun toString(): String {
        val sb = StringBuilder()
        for (y in (0 until height)) {
            for ( x in (0 until width)) {
                sb.append(data[Point(x, y).toOffset(width)].toRenderChar())
            }
            sb.append('\n')
        }
        return sb.toString()
    }

    fun count(predicate: (Int) -> Boolean): Int = data.count(predicate)
}