package aoc.day4

class Board(private val rows: List<List<Pair<Int, Boolean>>>) {

    companion object {
        fun fromInts(rows: List<List<Int>>) = Board(rows.map { it.map { num -> Pair(num, false) } })
    }

    fun withMarked(num: Int): Board =
        Board(rows.map { row -> row.map { if (it.first == num) Pair(it.first, true) else Pair(it.first, it.second) } })
    fun hasBingo(): Boolean = hasRowBingo() || hasColumnBingo()

    fun hasRowBingo(): Boolean = rows.any { row -> row.all { (_, m) -> m } }
    private fun hasColumnBingo() = rotate().hasRowBingo()

    fun score() = rows.flatten().sumOf { if (!it.second) it.first else 0 }
    private fun rotate(): Board = Board((0 until 5).map { col -> (0 until 5).map { row -> rows[row][col] } })

    override fun toString() = "\n BOARD \n" + rows.joinToString("\n") { row -> row.joinToString { if (it.second) "*" + it.first else it.first.toString() } } + "\n"
}