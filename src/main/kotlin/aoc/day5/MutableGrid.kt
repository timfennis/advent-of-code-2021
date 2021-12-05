package aoc.day5

/**
 * Optimized version of grid that simply mutates its internal representation
 */
class MutableGrid(private val width: Int, height: Int)
{
    private val data: MutableList<Int> = emptyArrayList(width, height)

    fun withLine(line: Line): MutableGrid {
        for (point in line.points()) {
            data[point.toOffset(width)]++
        }

        return this
    }

    fun count(predicate: (Int) -> Boolean): Int = data.count(predicate)
}