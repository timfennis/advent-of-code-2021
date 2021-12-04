package aoc.day4

fun main () {
    println(calculateWinningScore(exampleNumbers, exampleBoards).toScore())
    println(calculateLosingScore(exampleNumbers, exampleBoards).toScore())
    println(calculateWinningScore(numbers, boards).toScore())
    println(calculateLosingScore(numbers, boards).toScore())
}

tailrec fun calculateWinningScore(nums: List<Int>, boards: List<Board>, lastNum: Int = 0): Pair<Board, Int> =
    boards.firstOrNull { it.hasRowBingo() }?.let { Pair(it, lastNum) }
        ?: calculateWinningScore(nums.drop(1), boards.map { it.withMarked(nums.first()) }, nums.first())

tailrec fun calculateLosingScore(nums: List<Int>, boards: List<Board>, lastNum: Int = 0): Pair<Board, Int> =
    boards.onlyItemOrNull()?.let { Pair(it, lastNum) }
        ?: calculateLosingScore(nums.drop(1), boards.filterNot { it.hasBingo() }.map { it.withMarked(nums.first()) }, nums.first())

fun Pair<Board, Int>.toScore() = first.score() * second
fun List<Board>.onlyItemOrNull() = if(this.size == 1) this.firstOrNull() else null
