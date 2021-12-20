package aoc.day20

import aoc.Day
import aoc.splitLines

class DayTwenty : Day(20) {

    override val exampleSolution = listOf(35L, 3351L)

    override fun solvePartOne(input: String) = parseInput(input)
        .let { image -> (0 until 2).fold(image) { acc, _ -> enhance(acc) } }
        .let { image -> image.pixels.flatten().count { it } }
        .toLong()


    override fun solvePartTwo(input: String) = parseInput(input)
        .let { image -> (0 until 50).fold(image) { acc, _ -> enhance(acc) } }
        .also { image -> println(image.render()) }
        .let { image -> image.pixels.flatten().count { it } }
        .toLong()
}

private fun enhance(image: Image): Image {
    return image.enlarge().map { groupOfPixels ->
        image.enhancement.getOrNull(groupOfPixels.toOffset()) ?: throw RuntimeException("Invalid offset in enhancement string")
    }
}

private fun parseInput(input: String): Image {
    val (enhancement, image) = input.split("\n\n")

    val pixels = image.splitLines().map { it.map { c -> c == '#' } }

    return Image(pixels, enhancement.map { c -> c == '#' })
}


data class Image(val pixels: List<List<Boolean>>, val enhancement: List<Boolean>, val oob: Boolean = false) {

    private val width by lazy { pixels.first().size }
    private val height by lazy { pixels.size }

    fun enlarge() = Image(
        pixels = listOf(List(width + 2) { oob }) +
                pixels.map { listOf(oob) + it + listOf(oob) } +
                listOf(List(width + 2) { oob }),
        enhancement = enhancement,
        oob = oob
    )

    fun map(transform: (List<Boolean>) -> Boolean): Image = Image(
        pixels = pixels.mapIndexed { y, ys -> List(ys.size) { x -> transform(getNeighbours(x, y)) } },
        enhancement = enhancement,
        oob = enhancement[(0..8).map { oob }.toOffset()]
    )

    private fun getNeighbours(x: Int, y: Int) = listOf(
        getPixel(x - 1, y - 1),
        getPixel(x, y - 1),
        getPixel(x + 1, y - 1),

        getPixel(x - 1, y),
        getPixel(x, y),
        getPixel(x + 1, y),

        getPixel(x - 1, y + 1),
        getPixel(x, y + 1),
        getPixel(x + 1, y + 1),
    )

    private fun getPixel(x: Int, y: Int) = if (pixels.getOrNull(y) == null) {
        oob
    } else {
        pixels[y].getOrElse(x) { oob }
    }

    override fun toString() = "W: $width H: $height OOB: $oob"
    fun render() = pixels.joinToString("\n") { row -> row.joinToString("") { if (it) "#" else "." } }

}

private fun List<Boolean>.toOffset() = this.map { if (it) '1' else '0' }.joinToString("").toInt(2)